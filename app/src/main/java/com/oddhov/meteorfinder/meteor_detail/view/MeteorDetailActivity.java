package com.oddhov.meteorfinder.meteor_detail.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oddhov.meteorfinder.MeteorFinderApp;
import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.meteor_detail.DaggerMeteorDetailComponent;
import com.oddhov.meteorfinder.meteor_detail.MeteorDetailContract;
import com.oddhov.meteorfinder.meteor_detail.presentation.MeteorDetailModule;
import com.oddhov.meteorfinder.meteor_detail.presentation.MeteorDetailPresenter;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorDetailActivity extends AppCompatActivity implements MeteorDetailContract.View, OnMapReadyCallback {
    @BindView(R.id.meteorName)
    TextView mMeteorName;
    @BindView(R.id.meteorYear)
    TextView mMeteorYear;
    @BindView(R.id.meteorClass)
    TextView mMeteorClass;
    @BindView(R.id.meteorMass)
    TextView mMeteorMass;

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

        setupMap(savedInstanceState);

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
        MapsInitializer.initialize(getApplicationContext());
        mMap = googleMap;
        mMapView.onResume();

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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 2));
    }

    @Override
    public void dropMarker(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng));
    }

    @Override
    public void setName(String name) {
        mMeteorName.setText(name);
    }

    @Override
    public void setYear(String year) {
        mMeteorYear.setText(year);
    }

    @Override
    public void setMass(Integer format, String mass) {
        mMeteorMass.setText(getString(format, mass));
    }

    @Override
    public void setClassType(Integer format, String classType) {
        mMeteorClass.setText(getString(format, classType));
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupMap(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }
}
