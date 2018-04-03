package kesean.com.yoda_speaks.data.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kesean on 3/28/18.
 *
 * Adds HTTP Response Error Checking at a Global Level
 */

public class HttpErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        okhttp3.Response response = chain.proceed(original);

        // todo deal with the issues the way you need to
        if (response.code() == 500) {
            throw new HttpException("Something Went Wrong");

        }else if(response.code() == 404){
            throw new HttpException("Translation Not Found");
        }

        return response;
    }

    public class HttpException extends IOException {

        String s;
        public HttpException(String s) {
            this.s = s;
        }

        @Override
        public String getMessage() {
            return s;
        }

    }
}
