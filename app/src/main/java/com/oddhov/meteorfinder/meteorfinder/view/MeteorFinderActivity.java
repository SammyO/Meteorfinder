package com.oddhov.meteorfinder.meteorfinder.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oddhov.meteorfinder.MeteorFinderApp;
import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.meteorfinder.DaggerMeteorFinderComponent;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;
import com.oddhov.meteorfinder.meteorfinder.presentation.MeteorFinderModule;
import com.oddhov.meteorfinder.meteorfinder.presentation.MeteorFinderPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorFinderActivity extends AppCompatActivity implements MeteorFinderContract.View {
    @Inject
    MeteorFinderPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMeteorFinderComponent.builder()
                .applicationComponent(((MeteorFinderApp) getApplication()).getApplicationComponent())
                .meteorFinderModule(new MeteorFinderModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_meteorfinder);

        ButterKnife.bind(this);

        mPresenter.subscribe();
        mPresenter.updateData(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }
}
