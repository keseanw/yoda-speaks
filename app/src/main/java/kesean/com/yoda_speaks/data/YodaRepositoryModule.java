package kesean.com.yoda_speaks.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kesean.com.yoda_speaks.data.repository.Remote;
import kesean.com.yoda_speaks.data.repository.YodaDataSource;
import kesean.com.yoda_speaks.data.repository.remote.YodaRemoteDataSource;

/**
 * Created by Kesean on 3/28/18.
 */

@Module
public class YodaRepositoryModule {

    /*
    * Remote data source for API calls
    * */

    @Provides
    @Remote
    @Singleton
    public YodaDataSource provideRemoteDataSource(YodaRemoteDataSource yodaRemoteDataSource) {
        return yodaRemoteDataSource;
    }

    /*
    * Local data source
    * */

//    @Provides
//    @Local
//    @Singleton
//    public YodaDataSource provideLocalDataSource(YodaLocalDataSource yodaLocalDataSource) {
//        return yodaLocalDataSource;
//    }

}
