package plotnick.weathermap;

public record MainWeather(
        double temp,
        double feels_like,
        int pressure,
        int humidity)
{
}