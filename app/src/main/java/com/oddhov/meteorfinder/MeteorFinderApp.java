package com.oddhov.meteorfinder;

import android.app.Application;

import com.oddhov.meteorfinder.di.component.ApplicationComponent;
import com.oddhov.meteorfinder.di.component.DaggerApplicationComponent;
import com.oddhov.meteorfinder.di.module.ApplicationModule;
import com.oddhov.meteorfinder.di.module.DataSourcesModule;
import com.oddhov.meteorfinder.di.module.LocalDataSourceModule;
import com.oddhov.meteorfinder.di.module.NetworkDataSourceModule;
import com.oddhov.meteorfinder.utils.UtilsModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;

/**
 * Created by sammy on 07/09/17.
 */

public class MeteorFinderApp extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                return;
            }
            LeakCanary.install(this);
        }

        initializeDependencies();

    }

    private void initializeDependencies() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataSourcesModule(new DataSourcesModule())
                .networkDataSourceModule(new NetworkDataSourceModule())
                .utilsModule(new UtilsModule())
                .localDataSourceModule(new LocalDataSourceModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }

}
