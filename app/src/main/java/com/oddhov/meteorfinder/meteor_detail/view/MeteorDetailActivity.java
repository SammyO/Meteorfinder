package com.oddhov.meteorfinder.meteor_detail.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oddhov.meteorfinder.MeteorFinderApp;
import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.meteor_detail.DaggerMeteorDetailComponent;
import com.oddhov.meteorfinder.meteor_detail.MeteorDetailContract;
import com.oddhov.meteorfinder.meteor_detail.presentation.MeteorDetailModule;
import com.oddhov.meteorfinder.meteor_detail.presentation.MeteorDetailPresenter;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorDetailActivity extends AppCompatActivity implements MeteorDetailContract.View {
    @Inject
    MeteorDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMeteorDetailComponent.builder()
                .applicationComponent(((MeteorFinderApp) getApplication()).getApplicationComponent())
                .meteorDetailModule(new MeteorDetailModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_meteor_detail_view);
        ButterKnife.bind(this);

        setupToolbar();

        mPresenter.subscribe();
        mPresenter.setIntent(getIntent());
        mPresenter.initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void onBackPressed() {
        mPresenter.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void setScreenTitle(String title) {
        setTitle(title);
    }

    @Override
    public void closeActivityWithTransition(ScreenTransition screenTransition) {
        finish();
        overridePendingTransition(screenTransition.getEnter(), screenTransition.getExit());
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
