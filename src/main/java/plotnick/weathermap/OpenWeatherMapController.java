package plotnick.weathermap;


import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;

public class OpenWeatherMapController
{
    private OpenWeatherMapService OpenWeatherMapService;

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
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        Disposable disposableweathermap = OpenWeatherMapService.search(keyString, lon, lat)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())

                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        (this::handleResponsePhotos),
                        Throwable::printStackTrace);

    }

    private void handleResponseWeather(WeatherMapCurrent current)
    {


    }





}
