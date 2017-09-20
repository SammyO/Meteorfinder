package com.oddhov.meteorfinder.ui.meteor_detail.presentation;

import com.oddhov.meteorfinder.di.annotation.ActivityScope;
import com.oddhov.meteorfinder.ui.meteor_detail.MeteorDetailContract;
import com.oddhov.meteorfinder.ui.meteor_detail.view.MeteorDetailActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by sammy on 13/09/17.
 */

@ActivityScope
@Module
public class MeteorDetailModule {
    private final MeteorDetailActivity mView;

    public MeteorDetailModule(MeteorDetailActivity view) {
        this.mView = view;
    }

    @Provides
    MeteorDetailContract.View provideView() {
        return mView;
    }
}
