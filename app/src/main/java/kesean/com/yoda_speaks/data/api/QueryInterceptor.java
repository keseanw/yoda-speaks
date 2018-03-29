package kesean.com.yoda_speaks.data.api;

import java.io.IOException;

import kesean.com.yoda_speaks.data.Config;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kesean on 3/28/18.
 */

public class QueryInterceptor implements Interceptor {
    private static final String APP_ID = "APPID";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(APP_ID, Config.YODA_API_APP_ID)
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);
        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}
