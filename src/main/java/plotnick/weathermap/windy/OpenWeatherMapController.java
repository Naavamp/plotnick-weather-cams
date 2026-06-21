package plotnick.weathermap.windy;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import plotnick.weathermap.Geocoder;
import plotnick.weathermap.OpenWeatherMapService;
import plotnick.weathermap.WeatherMap;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenWeatherMapController
{
    private OpenWeatherMapService openWeatherMapService;
    private WindyService windyService;

    private JTextField search;

    private JLabel latLabel;
    private JLabel lonLabel;


    private JLabel templabel;
    private JLabel feels_likelabel;
    private JLabel description;

    private final JLabel[] picLabels;
    private final int radius = 10;


    public OpenWeatherMapController(OpenWeatherMapService openWeatherMapService, WindyService windyService,
                                    JTextField search, JLabel latLabel, JLabel lonLabel,
                                    JLabel templabel, JLabel feels_likelabel, JLabel description, JLabel[] picLabels)
    {
        this.openWeatherMapService = openWeatherMapService;
        this.windyService = windyService;
        this.search = search;
        this.latLabel = latLabel;
        this.lonLabel = lonLabel;
        this.templabel = templabel;
        this.feels_likelabel = feels_likelabel;
        this.description = description;
        this.picLabels = picLabels;

    }

    public void doSearch()
    {

        String city = search.getText();
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();


        Disposable disposable = openWeatherMapService.currentGeolocation(city, keyString)
                .subscribeOn(Schedulers.io())
                .flatMap(geocoder -> {

                    Geocoder geocoder1 = geocoder.get(0);

                    latLabel.setText(String.valueOf(geocoder1.lat()));
                    lonLabel.setText(String.valueOf(geocoder1.lon()));

                    return openWeatherMapService.currentWeather(
                            geocoder1.lat(),
                            geocoder1.lon(),
                            keyString,
                            "imperial");
                })
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(this::handleResponseWeather);


        ApiKey windyKey = new ApiKey("windy");
        String windyKeyString = windyKey.get();

        Disposable disposableWindyResults = windyService.getResults(
                        windyKeyString, latLabel + "," + lonLabel + "," + radius)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        (this::handleResponse),
                        Throwable::printStackTrace);

    }


    private void handleResponseWeather(WeatherMap weatherMap)
    {
        templabel.setText(String.valueOf(weatherMap.main().temp()));
        feels_likelabel.setText(String.valueOf(weatherMap.main().feels_like()));
        description.setText(weatherMap.weather().get(0).description());
    }

    private void handleResponse(Results results)
    {
        Webcams[] webcams = results.webcams();
        for (int i = 0; i < webcams.length; i++)
        {
            try
            {
                Images images = webcams[i].images();
                Current current = images.current();
                ImageIcon imageIcon = new ImageIcon(new URL(current.preview()));
                picLabels[i].setIcon(imageIcon);
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
        }
    }





}
