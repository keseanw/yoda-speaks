package kesean.com.yoda_speaks.ui.yoda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import kesean.com.yoda_speaks.R;
import kesean.com.yoda_speaks.data.model.YodaResponse;
import kesean.com.yoda_speaks.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements YodaContract.View {
    @BindView(R.id.text_notification)
    TextView notificationText;

    @Inject
    YodaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializePresenter();
    }

    private void initializePresenter() {
        DaggerYodaComponent.builder()
                .yodaPresenterModule(new YodaPresenterModule(this))
                .yodaRepositoryComponent(getYodaRepositoryComponent())
                .build()
                .inject(this);
    }

    @Override
    public void showYodaTranslation(YodaResponse yodaResponse) {
        //TODO get yoda translation
    }

    @Override
    public void clearYodaTranslation() {
        //TODO clear text button
    }

    @Override
    public void showNoDataMessage() {
        showNotification(getString(R.string.msg_no_data));
    }

    @Override
    public void showErrorMessage(String error) {
        showNotification(error);
    }

    @Override
    public void stopLoadingIndicator() {
        //TODO loading indicator setup
    }

    @Override
    public void showEmptySearchResult() {
        showNotification(getString(R.string.msg_empty_search_result));
    }

    @Override
    public void clearView() {
        notificationText.setVisibility(View.GONE);
    }

    /*
    * Method for displaying error message in view
    * */
    private void showNotification(String message) {
        notificationText.setVisibility(View.VISIBLE);
        notificationText.setText(message);
    }
}