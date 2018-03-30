package kesean.com.yoda_speaks.ui.yoda;

import dagger.Component;
import kesean.com.yoda_speaks.data.YodaRepositoryComponent;
import kesean.com.yoda_speaks.ui.base.ActivityScope;
import kesean.com.yoda_speaks.util.SchedulerModule;

/**
 * Created by Kesean on 3/29/18.
 */

@ActivityScope
@Component(modules = {YodaPresenterModule.class, SchedulerModule.class}, dependencies = {
        YodaRepositoryComponent.class
})
public interface YodaComponent {

    void inject(MainActivity mainActivity);
}
