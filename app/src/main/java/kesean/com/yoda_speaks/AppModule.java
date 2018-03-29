package kesean.com.yoda_speaks;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kesean on 3/28/18.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }
}
