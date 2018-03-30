package kesean.com.yoda_speaks.data.repository;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.yoda_speaks.data.Config;
import kesean.com.yoda_speaks.data.model.YodaResponse;

/**
 * Created by Kesean on 3/28/18.
 */

public class YodaRepository implements YodaDataSource {
    private YodaDataSource remoteDataSource;
    private SharedPreferences sharedPreferences;

    @Inject
    public YodaRepository(SharedPreferences preferences, @Remote YodaDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.sharedPreferences = preferences;
    }
    @Override
    public Flowable<YodaResponse> loadTranslation(String englishText) {
        return remoteDataSource.loadTranslation(englishText);
    }

    @Override
    public void setText(String englishText) {
        sharedPreferences.edit()
                .putString(Config.YODA_SEARCH_VALUE, englishText)
                .apply();
    }

    @Override
    public String getText() {
        return sharedPreferences.getString(Config.YODA_SEARCH_VALUE, "none");
    }

//    TODO ADD SHARED PREFS FOR TRANSLATION RESULT AS WELL (GET & SET)
}
