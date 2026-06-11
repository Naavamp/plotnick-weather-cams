package plotnick.weathermap;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocoderService
{
    @GET("/geo/1.0/direct")
    Single<Geocoder> currentGeolocation(@Query("q") String city,
                                        @Query("appid") String appid);

}
