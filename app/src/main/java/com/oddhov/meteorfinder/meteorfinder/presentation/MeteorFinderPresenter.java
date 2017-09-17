package com.oddhov.meteorfinder.meteorfinder.presentation;

import android.content.Intent;
import android.util.Log;

import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorDetailActivity;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorItemOnClickListener;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;
import com.oddhov.meteorfinder.meteorfinder.view.MeteorAdapter;
import com.oddhov.meteorfinder.utils.Constants;
import com.oddhov.meteorfinder.utils.DateUtils;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import javax.inject.Inject;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorFinderPresenter implements MeteorFinderContract.Presenter, MeteorItemOnClickListener {
    private DataSources mDataSources;
    private MeteorFinderContract.View mView;
    private MeteorAdapter mMeteorAdapter;

    @Inject
    DateUtils mDateUtils;

    @Inject
    public MeteorFinderPresenter(DataSources dataSources, MeteorFinderContract.View view,
                                 MeteorAdapter meteorAdapter) {
        this.mDataSources = dataSources;
        this.mView = view;
        this.mMeteorAdapter = meteorAdapter;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void initialize() {
        mMeteorAdapter.setMeteorItemOnClickListener(this);
        mView.setAdapter(mMeteorAdapter);

        if (mDataSources.hasLocalData()) {
            showContent();
        } else {
            mView.showLoading();
        }
        mDataSources.getDataFromServer()
                .subscribe(this::showContent,
                        this::handleGetDataError
                );

        Log.e("Test", "mDateUtils: " + mDateUtils.getYearFromDateString("2012-01-01T00:00:00.000"));
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onRefresh() {
        updateData();
    }


    @Override
    public void onItemClick(String meteorId) {
        Intent intent = new Intent(mView.getContext(), MeteorDetailActivity.class);
        intent.putExtra(Constants.METEOR_ID, meteorId);
        mView.startActivityWithTransition(intent, ScreenTransition.NEXT_SLIDING_SLIDING);
    }


    private void updateData() {
        mDataSources.getDataFromServer()
                .subscribe(this::showContent,
                        this::handleGetDataError
                );
    }

    private void showContent() {
        Log.e("Presenter", "stored data: " + mDataSources.getAllMeteors().size());
        mMeteorAdapter.setData(mDataSources.getAllMeteors());
        mView.showContent();
    }

    private void handleGetDataError(Throwable throwable) {
        Log.e("Presenter", "Api error " + throwable.toString());
        mView.showError();
    }
}
