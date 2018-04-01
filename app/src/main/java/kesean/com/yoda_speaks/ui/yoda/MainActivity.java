package kesean.com.yoda_speaks.ui.yoda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kesean.com.yoda_speaks.R;
import kesean.com.yoda_speaks.data.model.YodaResponse;
import kesean.com.yoda_speaks.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements YodaContract.View {
    @BindView(R.id.text_notification)
    TextView notificationText;
    @BindView(R.id.yodaResponse)
    TextView yodaTranslation;
    @BindView(R.id.textInputEditText)
    EditText englishTextInput;
    @BindView(R.id.clearResult)
    ImageView clearTranslationView;
    @BindView(R.id.progressBar)
    ProgressBar loadingIndicator;
    @BindView(R.id.yoda_response_card)
    CardView yodaResponseCardView;

    @Inject
    YodaPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initializePresenter();

        englishTextInput.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_DONE) {
                presenter.loadTranslation(textView.getText().toString());
                return true;
            }
            return false;
        });

        englishTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() <= 0) {
                    clearTranslationView.setVisibility(View.GONE);
                }else {
                    clearTranslationView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
        yodaResponseCardView.setVisibility(View.VISIBLE);
        yodaTranslation.setText(yodaResponse.getContents().getTranslated());
    }

    @Override
    public void hideYodaTranslation() {
        yodaResponseCardView.setVisibility(View.GONE);
    }

    @OnClick(R.id.clearResult)
    public void clearTranslationButton(){
        clearYodaTranslation();
    }

    @Override
    public void clearYodaTranslation() {
        if(englishTextInput.getText() != null || yodaTranslation.getText() != null) {
            englishTextInput.getText().clear();
            yodaTranslation.setText("");
        }
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
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingIndicator() {
        loadingIndicator.setVisibility(View.VISIBLE);
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
