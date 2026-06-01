import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;
import plotnick.weathermap.OpenWeatherFrame;
import plotnick.weathermap.OpenWeatherMapService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherMapServiceTest
{
    @Test
    public void testCurrentWeather()
    {
            //given
            ApiKey apiKey = new ApiKey();
            String keyString = apiKey.get();
            OpenWeatherMapService service = new OpenWeatherMapService().create();

            //when


            //then



    }

}
