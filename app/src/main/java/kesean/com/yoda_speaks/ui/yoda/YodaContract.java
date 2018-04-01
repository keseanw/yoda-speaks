package kesean.com.yoda_speaks.ui.yoda;

import com.squareup.haha.perflib.Main;

import kesean.com.yoda_speaks.data.model.YodaResponse;
import kesean.com.yoda_speaks.ui.base.BasePresenter;

/**
 * Created by Kesean on 3/29/18.
 */

public class YodaContract {

    interface View{

        void showYodaTranslation(YodaResponse yodaResponse);

        void clearYodaTranslation();

        void showNoDataMessage();

        void showErrorMessage(String error);

        void stopLoadingIndicator();

        void showLoadingIndicator();

        void hideYodaTranslation();

        void showEmptySearchResult();

        void clearView();

    }

    interface YodaPresenter extends BasePresenter<View> {

        void loadTranslation(String inputValue);

        void setInputValue(String inputValue);

        String getInputValue();

    }
}
