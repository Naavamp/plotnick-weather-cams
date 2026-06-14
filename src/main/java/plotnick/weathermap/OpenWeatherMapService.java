package plotnick.weathermap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import javax.swing.*;
import java.util.List;


public interface OpenWeatherMapService
{

   @GET("/data/2.5/weather")
   Single<WeatherMap> currentWeather(@Query("lat") double lat,
                                        @Query("lon") double lon,
                                        @Query("appid") String appid,

                                     @Query("units") String units);

   @GET("/geo/1.0/direct")
   Single<List<Geocoder>> currentGeolocation(@Query("q") String city,
                                            @Query("appid") String appid);

}
