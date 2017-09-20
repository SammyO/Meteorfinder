package com.oddhov.meteorfinder.ui.meteorfinder;

import com.oddhov.meteorfinder.di.annotation.ActivityScope;
import com.oddhov.meteorfinder.di.component.ApplicationComponent;
import com.oddhov.meteorfinder.ui.meteorfinder.presentation.MeteorFinderModule;
import com.oddhov.meteorfinder.ui.meteorfinder.view.MeteorFinderActivity;

import dagger.Component;

/**
 * Created by sammy on 13/09/17.
 */

@ActivityScope
@Component(dependencies = ApplicationComponent.class,
        modules = {
                MeteorFinderModule.class
        })
public interface MeteorFinderComponent {
    void inject(MeteorFinderActivity activity);
}