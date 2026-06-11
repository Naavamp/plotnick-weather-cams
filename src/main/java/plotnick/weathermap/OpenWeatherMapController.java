package plotnick.weathermap;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;

public class OpenWeatherMapController
{
    private OpenWeatherMapService openWeatherMapService;

    private JTextField search;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel templabel;
    private JLabel feels_likelabel;
    private JLabel description;


    public OpenWeatherMapController(OpenWeatherMapService openWeatherMapService,
                                    JTextField search, JLabel latLabel, JLabel lonLabel,
                                    JLabel templabel, JLabel feels_likelabel, JLabel description)
    {
        this.openWeatherMapService = openWeatherMapService;
        this.search = search;
        this.latLabel = latLabel;
        this.lonLabel = lonLabel;
        this.templabel = templabel;
        this.feels_likelabel = feels_likelabel;
        this.description = description;

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
                            keyString);
                })
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(this::handleResponseWeather);

    }


    private void handleResponseWeather(WeatherMap weatherMap)
    {
        templabel.setText(String.valueOf(weatherMap.main().temp()));
        feels_likelabel.setText(String.valueOf(weatherMap.main().feels_like()));
        description.setText(weatherMap.weather().get(0).description());
    }



}
