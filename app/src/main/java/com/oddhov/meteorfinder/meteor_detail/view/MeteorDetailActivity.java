package com.oddhov.meteorfinder.meteor_detail.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
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

public class MeteorDetailActivity extends AppCompatActivity implements MeteorDetailContract.View, OnMapReadyCallback {
    @Inject
    MeteorDetailPresenter mPresenter;

    private MapView mMapView;
    private GoogleMap mMap;

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

        setupMap();

        mPresenter.subscribe();
        mPresenter.setIntent(getIntent());
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // This is called now instead of in onCreate, because only after the map is ready we
        // can do operations on it.
        mPresenter.initialize();
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

    @Override
    public void moveCamera(LatLng latLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupMap() {
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.getMapAsync(this);
    }
}
