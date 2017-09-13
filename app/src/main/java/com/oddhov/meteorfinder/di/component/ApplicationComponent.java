package com.oddhov.meteorfinder.di.component;

import com.oddhov.meteorfinder.di.module.ApplicationModule;
import com.oddhov.meteorfinder.di.module.DataSourcesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sammy on 07/09/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataSourcesModule.class})
public interface ApplicationComponent {
}
