package plotnick.weathermap;

import java.util.List;

public record WeatherMap(
        Coord coord,
        List<Weather> weather,
        MainWeather main,
        int timezone)
{
}