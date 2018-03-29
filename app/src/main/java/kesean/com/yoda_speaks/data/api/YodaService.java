package kesean.com.yoda_speaks.data.api;

import io.reactivex.Flowable;
import kesean.com.yoda_speaks.data.model.YodaResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kesean on 3/28/18.
 */

public interface YodaService {

    @GET("yoda.json")
    Flowable<YodaResponse> getYodaResponse(@Query("text") String text);

}
