package kesean.com.yoda_speaks.data;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kesean.com.yoda_speaks.data.api.HeaderInterceptor;
import kesean.com.yoda_speaks.data.api.HttpErrorInterceptor;
import kesean.com.yoda_speaks.data.api.NetworkConnectivityInterceptor;
import kesean.com.yoda_speaks.data.api.YodaService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kesean on 3/28/18.
 */

@Module
public class ApiServiceModule {

    private static final String BASE_URL = "base_url";

     /*
    * Dagger method binding
    * */

    @Provides
    @Named(BASE_URL)
    String provideBaseUrl() {
        return Config.YODA_API_HOST;
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    NetworkConnectivityInterceptor provideNetworkConnectivityInterceptor(ConnectivityManager connectivityManager) {
        return new NetworkConnectivityInterceptor(connectivityManager);
    }

    @Provides
    @Singleton
    HttpErrorInterceptor provideHttpErrorInterceptor() {return new HttpErrorInterceptor();}

    @Provides
    @Singleton
    HeaderInterceptor provideHeaderInterceptor() {
        return new HeaderInterceptor();
    }


    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(HttpErrorInterceptor httpErrorInterceptor, NetworkConnectivityInterceptor networkConnectivityInterceptor, HeaderInterceptor headerInterceptor, HttpLoggingInterceptor httpInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpErrorInterceptor)
                .addInterceptor(networkConnectivityInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpInterceptor)
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideRxJavaAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, Converter.Factory converterFactory,
                             CallAdapter.Factory callAdapterFactory, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    YodaService provideYodaService(Retrofit retrofit) {
        return retrofit.create(YodaService.class);
    }
}
