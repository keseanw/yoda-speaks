package kesean.com.yoda_speaks.data.repository;

import io.reactivex.Flowable;
import kesean.com.yoda_speaks.data.model.YodaResponse;

/**
 * Created by Kesean on 3/28/18.
 */

public interface YodaDataSource {

    Flowable<YodaResponse> loadTranslation(String englishText);

    void setText(String englishText);

    String getText();
}
