package com.oddhov.meteorfinder.meteorfinder.presentation;

import com.oddhov.meteorfinder.di.annotation.ActivityScope;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;
import com.oddhov.meteorfinder.meteorfinder.view.MeteorFinderActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 13/09/17.
 */

@ActivityScope
@Module
public class MeteorFinderModule {
    private final MeteorFinderActivity mView;

    public MeteorFinderModule(MeteorFinderActivity view) {
        this.mView = view;
    }

    @Provides
    MeteorFinderContract.View provideView() {
        return mView;
    }
}
