package kesean.com.yoda_speaks.data.repository.remote;

import javax.inject.Inject;

import io.reactivex.Flowable;
import kesean.com.yoda_speaks.data.api.YodaService;
import kesean.com.yoda_speaks.data.model.YodaResponse;
import kesean.com.yoda_speaks.data.repository.YodaDataSource;

/**
 * Created by Kesean on 3/28/18.
 */

public class YodaRemoteDataSource implements YodaDataSource {
    private YodaService yodaService;

    @Inject
    public YodaRemoteDataSource(YodaService yodaService) {
        this.yodaService = yodaService;
    }


    @Override
    public Flowable<YodaResponse> loadTranslation(String englishText) {
        return yodaService.getYodaResponse(englishText);
    }

    /*
    * Not in Use Here
    * */
    @Override
    public void setText(String englishText) {

    }

    /*
    * Not in Use Here
    * */
    @Override
    public String getText() {
        return null;
    }

    /*
    * Not in Use Here
    * */
    @Override
    public void clearSharedPrefs() {

    }
}
