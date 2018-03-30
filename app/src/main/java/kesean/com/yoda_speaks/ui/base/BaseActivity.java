package kesean.com.yoda_speaks.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.support.v7.app.AppCompatActivity;

import kesean.com.yoda_speaks.AndroidApplication;
import kesean.com.yoda_speaks.data.YodaRepositoryComponent;

/**
 * Created by Kesean on 3/29/18.
 */

public class BaseActivity extends AppCompatActivity {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public YodaRepositoryComponent getYodaRepositoryComponent() {
        return ((AndroidApplication) getApplication()).getYodaRepositoryComponent();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
