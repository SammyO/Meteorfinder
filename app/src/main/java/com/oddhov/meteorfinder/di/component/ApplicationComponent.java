package com.oddhov.meteorfinder.di.component;

import com.oddhov.meteorfinder.MeteorFinderApp;
import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.di.module.ApplicationModule;
import com.oddhov.meteorfinder.di.module.DataSourcesModule;
import com.oddhov.meteorfinder.utils.DateUtils;
import com.oddhov.meteorfinder.utils.LocationUtils;
import com.oddhov.meteorfinder.utils.QueryUtils;
import com.oddhov.meteorfinder.utils.UtilsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sammy on 07/09/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataSourcesModule.class, UtilsModule.class})
public interface ApplicationComponent {
    DataSources getDataSources();

    DateUtils getDateUtils();

    QueryUtils getQueryUtils();

    LocationUtils getLocationUtils();

    void inject(MeteorFinderApp o);
}
