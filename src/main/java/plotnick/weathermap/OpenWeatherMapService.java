package plotnick.weathermap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import javax.swing.*;


public interface OpenWeatherMapService
{

   @GET("/data/2.5/weather")
   Single<WeatherMap> currentWeather(@Query("lat") double lat,
                                        @Query("lon") double lon,
                                        @Query("appid") String appid);

}
