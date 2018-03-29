package kesean.com.yoda_speaks.data.repository;

import io.reactivex.Flowable;
import kesean.com.yoda_speaks.data.model.YodaResponse;

/**
 * Created by Kesean on 3/28/18.
 */

public class YodaRepository implements YodaDataSource {
    @Override
    public Flowable<YodaResponse> loadTranslation(String englishText) {
        return null;
    }

    @Override
    public void setText(String englishText) {

    }

    @Override
    public String getText() {
        return null;
    }
}
