package kesean.com.yoda_speaks.ui.yoda;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
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
    public YodaPresenter(YodaRepository repository, YodaContract.View view, @RunOn(SchedulerType.IO) Scheduler ioScheduler, @RunOn(SchedulerType.UI) Scheduler uiScheduler) {
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

    }

    @Override @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onDetach() {
        disposeBag.clear();
    }

    @Override
    public void loadTranslation(String inputValue) {

    }

    @Override
    public void setInputValue(String inputValue) {

    }

    @Override
    public String getInputValue() {
        return null;
    }
}
