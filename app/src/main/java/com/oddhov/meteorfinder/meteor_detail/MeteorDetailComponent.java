package com.oddhov.meteorfinder.meteor_detail;

import com.oddhov.meteorfinder.di.annotation.ActivityScope;
import com.oddhov.meteorfinder.di.component.ApplicationComponent;
import com.oddhov.meteorfinder.meteor_detail.presentation.MeteorDetailModule;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorDetailActivity;

import dagger.Component;

/**
 * Created by sammy on 13/09/17.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                MeteorDetailModule.class,
        })
public interface MeteorDetailComponent {
    void inject(MeteorDetailActivity activity);
}