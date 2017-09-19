package com.oddhov.meteorfinder.meteor_detail.presentation;

import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.meteor_detail.MeteorDetailContract;
import com.oddhov.meteorfinder.utils.Constants;
import com.oddhov.meteorfinder.utils.DateUtils;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import java.util.Date;

import javax.inject.Inject;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorDetailPresenter implements MeteorDetailContract.Presenter<MeteorDetailContract.View> {
    private DataSources mDataSources;
    private MeteorDetailContract.View mView;
    private Intent mIntent;
    private Meteor mMeteor;
    private DateUtils mDateUtils;

    @Inject
    public MeteorDetailPresenter(DataSources dataSources, DateUtils databaseUtils) {
        this.mDataSources = dataSources;
        this.mDateUtils = databaseUtils;
    }

    @Override
    public void subscribe(MeteorDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void initialize() {
        String meteorId = mIntent.getStringExtra(Constants.METEOR_ID);
        this.mMeteor = mDataSources.getMeteorWithId(meteorId);
        mView.setScreenTitle(mMeteor.getName());

        setupMap();

        setupUI();
    }

    @Override
    public void unSubscribe() {
        this.mView = null;
    }

    @Override
    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    @Override
    public void onBackPressed() {
        mView.closeActivityWithTransition(ScreenTransition.BACK_SLIDING_SLIDING);
    }

    private void setupMap() {
        final LatLng meteorPosition = new LatLng(mMeteor.getLatitude(), mMeteor.getLongitude());

        mView.dropMarker(meteorPosition);
        mView.moveCamera(meteorPosition);
    }

    private void setupUI() {
        Date date = mDateUtils.getYearFromDateString(mMeteor.getYear());
        String year = mDateUtils.getYearFromDate(date);
        mView.setName(mMeteor.getName());
        mView.setYear(year);
        mView.setMass(R.string.meteor_mass, mMeteor.getMass());
        mView.setClassType(R.string.meteor_class, mMeteor.getClassType());
    }
}
