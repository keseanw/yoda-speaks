package kesean.com.yoda_speaks;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import kesean.com.yoda_speaks.data.DaggerYodaRepositoryComponent;
import kesean.com.yoda_speaks.data.YodaRepositoryComponent;
import timber.log.Timber;

/**
 * Created by Kesean on 3/28/18.
 */

public class AndroidApplication extends Application {

    private YodaRepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Dagger module init
        initializeDependencies();

        if (BuildConfig.DEBUG) {
            //Timber logging & Stetho setup
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
        }

        //leak canary for memory leaks
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initializeDependencies() {
        repositoryComponent = DaggerYodaRepositoryComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public YodaRepositoryComponent getYodaRepositoryComponent() {
        return repositoryComponent;
    }
}
