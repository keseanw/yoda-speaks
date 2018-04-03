package kesean.com.yoda_speaks.data.api;

import java.io.IOException;

import kesean.com.yoda_speaks.data.Config;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kesean on 3/28/18.
 *
 * Adds Header to every API request
 */

public class HeaderInterceptor implements Interceptor {
    private static final String APP_ID = "X-Mashape-Key";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        original = original.newBuilder()
                .addHeader(APP_ID, Config.YODA_API_APP_ID)
                .build();

        Response response = chain.proceed(original);

        return response;
    }
}
