package plotnick.weathermap;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;
import plotnick.weathermap.OpenWeatherMapService;
import plotnick.weathermap.WeatherMap;
import plotnick.weathermap.WeatherMapFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherMapServiceTest
{
    @Test
    public void CurrentWeather()
    {
        //given
        ApiKey apiKey1 = new ApiKey();
        String keyString = apiKey1.get();

        OpenWeatherMapService service = new WeatherMapFactory().create();


        //when
        List<Geocoder> georesult = service.currentGeolocation("new york", keyString).blockingGet();
        WeatherMap weatherResult = service.currentWeather(40.7128, -74.0060, keyString)
                .blockingGet();

        //then
        assertNotNull(weatherResult);
        assertNotNull(weatherResult.main());
        assertNotNull(weatherResult.weather());

    }

}
