package com.oddhov.meteorfinder.utils;

import com.oddhov.meteorfinder.di.annotation.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 17/09/17.
 */

@ActivityScope
@Module
public class UtilsModule {
    public UtilsModule() {
    }

    @Provides
    QueryUtils provideQueryUtils() {
        return new QueryUtils();
    }

    @Provides
    DateUtils provideDateUtils() {
        return new DateUtils();
    }

    @Provides
    LocationUtils provideLocationUtils() {
        return new LocationUtils();
    }
}
