package kesean.com.yoda_speaks.data;

import javax.inject.Singleton;

import dagger.Component;
import kesean.com.yoda_speaks.AppModule;
import kesean.com.yoda_speaks.data.repository.YodaRepository;

/**
 * Created by Kesean on 3/28/18.
 */

@Singleton
@Component(modules = { YodaRepositoryModule.class, AppModule.class, ApiServiceModule.class, PrefsModule.class})
public interface YodaRepositoryComponent {
    YodaRepository provideYodaRepository();
}
