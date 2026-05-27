package plotnick.weathermap;

public record WeatherMap(double lat, double lon, String timezone, int timezone_offset, WeatherMapCurrent current)
{

}
