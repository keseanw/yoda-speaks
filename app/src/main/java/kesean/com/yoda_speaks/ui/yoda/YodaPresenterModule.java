package kesean.com.yoda_speaks.ui.yoda;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kesean on 3/29/18.
 */

@Module
public class YodaPresenterModule {
    private YodaContract.View view;

    public YodaPresenterModule(YodaContract.View view) {
        this.view = view;
    }

    @Provides
    public YodaContract.View provideView() {
        return view;
    }
}
