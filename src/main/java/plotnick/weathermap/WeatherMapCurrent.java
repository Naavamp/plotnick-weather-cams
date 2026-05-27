package plotnick.weathermap;

public record WeatherMapCurrent(double dt,double sunrise, double sunset, double temp, double feels_like,
                                double pressure, int humidity, double dew_point,double uvi, double clouds, double visibility,
                                double wind_speed,double wind_deg, double wind_gust, weather weather)
{
}
