package plotnick.weathermap;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.util.List;

public class OpenWeatherMapController
{
    private OpenWeatherMapService OpenWeatherMapService;
    private GeocoderService GeocoderService;

    private JTextField search;
    private JLabel latLabel;
    private JLabel lonLabel;
    private JLabel templabel;
    private JLabel feels_likelabel;
    private JLabel description;


    public OpenWeatherMapController(OpenWeatherMapService openWeatherMapService, JTextField search,
                                    JLabel latLabel, JLabel lonLabel, JLabel templabel, JLabel feels_likelabel, JLabel description)
    {
        this.OpenWeatherMapService = openWeatherMapService;
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


        Disposable disposable = GeocoderService.currentGeolocation(city, keyString)
                .subscribeOn(Schedulers.io())
                .flatMap(geocoder -> {
                    latLabel.setText(String.valueOf(geocoder.lat()));
                    lonLabel.setText(String.valueOf(geocoder.lon()));

                    return OpenWeatherMapService.currentWeather(
                            geocoder.lat(),
                            geocoder.lon(),
                            keyString);
                })
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(this::handleResponseWeather);

    }



    private void handleGeocoderResponse(Geocoder geocoders)
    {
        latLabel.setText(String.valueOf(geocoders.lat()));
        lonLabel.setText(String.valueOf(geocoders.lon()));


    }

    private void handleResponseWeather(WeatherMap weatherMap)
    {
        templabel.setText(String.valueOf(weatherMap.current().temp()));
        feels_likelabel.setText(String.valueOf(weatherMap.current().feels_like()));
        description.setText(String.valueOf(weatherMap.current().weather().description()));
    }



}
