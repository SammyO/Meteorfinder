package com.oddhov.meteorfinder.di.module;

import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.data.DataSourcesImpl;
import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.data.network.NetworkDataSource;
import com.oddhov.meteorfinder.di.annotation.LocalScope;
import com.oddhov.meteorfinder.di.annotation.NetworkScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 07/09/17.
 */

@Module(includes = {NetworkDataSourceModule.class, LocalDataSourceModule.class})
public class DataSourcesModule {
    @Singleton
    @Provides
    DataSources provideDataSource(@LocalScope LocalDataSource localDataSource,
                                  @NetworkScope NetworkDataSource networkDataSource) {
        return new DataSourcesImpl(localDataSource, networkDataSource);
    }
}
