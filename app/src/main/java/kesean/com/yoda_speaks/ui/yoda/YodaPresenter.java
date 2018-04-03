package kesean.com.yoda_speaks.ui.yoda;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kesean.com.yoda_speaks.data.model.YodaResponse;
import kesean.com.yoda_speaks.data.repository.YodaRepository;
import kesean.com.yoda_speaks.util.RunOn;
import kesean.com.yoda_speaks.util.SchedulerType;

/**
 * Created by Kesean on 3/29/18.
 */

public class YodaPresenter implements YodaContract.YodaPresenter, LifecycleObserver {
    private YodaRepository repository;

    private YodaContract.View view;

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    private CompositeDisposable disposeBag;

    @Inject
    public YodaPresenter(YodaRepository repository, YodaContract.View view,
                         @RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler uiScheduler) {
        this.repository = repository;
        this.view = view;
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;

        // Initialize this presenter to be lifecycle-aware when a view is a lifecycle owner.
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }

        disposeBag = new CompositeDisposable();

    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onAttach() {
    // based on shared prefs- check if input value is saved
        if(!getInputValue().equals("none")){

            loadTranslation(getInputValue());
        }
    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposeBag.clear();
    }

    /*
   * Getting and Subscribing to the Yoda Translation
   * */

    @Override
    public void loadTranslation(String inputValue) {
        view.showLoadingIndicator();
        view.hideYodaTranslation();

        //Add query to shared preferences
        if(!inputValue.isEmpty()) {
            setInputValue(inputValue);
        }

        Disposable disposable = repository.loadTranslation(inputValue)
                .filter(translation -> translation.getContents().getTranslated() != null)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::handleReturnedData, this::handleError);
        disposeBag.add(disposable);
    }

    /*
   * Set Input Value in Shared Preferences
   * */

    @Override
    public void setInputValue(String inputValue) {
        repository.setText(inputValue);
    }

    /*
   * Get Input Value from Shared Preferences
   * */

    @Override
    public String getInputValue() {
        return repository.getText();
    }

    /*
   * Clear Shared Preference Storage
   * */

    @Override
    public void clearSavedPrefs() {
        repository.clearSharedPrefs();
    }

    /*
   * Handle any error
   * */

    private void handleError(Throwable error) {
        view.stopLoadingIndicator();

        view.showErrorMessage(error.getLocalizedMessage());
    }

    /*
    * Handle the API Response Object
    * */

    private void handleReturnedData(YodaResponse obj) {
        view.stopLoadingIndicator();

        if (obj.getContents().getTranslated() != null && !obj.getContents().getTranslated().isEmpty()) {
            view.showYodaTranslation(obj);
        } else {
            view.showNoDataMessage();
        }
    }
}
