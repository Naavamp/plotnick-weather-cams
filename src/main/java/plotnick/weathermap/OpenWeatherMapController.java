package plotnick.weathermap;


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



}
