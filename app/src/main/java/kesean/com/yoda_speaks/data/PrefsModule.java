package kesean.com.yoda_speaks.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kesean on 3/28/18.
 */

@Module
public class PrefsModule {
    /*
    * Shared preferences module
    * */

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}