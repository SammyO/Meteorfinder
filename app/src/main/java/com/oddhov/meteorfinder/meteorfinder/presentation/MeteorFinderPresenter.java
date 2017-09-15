package com.oddhov.meteorfinder.meteorfinder.presentation;

import android.util.Log;

import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;

import javax.inject.Inject;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorFinderPresenter implements MeteorFinderContract.Presenter {
    private DataSources mDataSources;
    private MeteorFinderContract.View mView;

    @Inject
    public MeteorFinderPresenter(DataSources dataSources, MeteorFinderContract.View view) {
        this.mDataSources = dataSources;
        this.mView = view;
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void initialize() {
        if (mDataSources.hasLocalData()) {
            mView.showContent(mDataSources.getStoredData());
        } else {
            mView.showLoading();
        }
        mDataSources.getDataFromServer()
                .subscribe(this::handleGetDataSuccess,
                        this::handleGetDataError
                );
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onRefresh() {
        updateData();
    }

    private void updateData() {
        mDataSources.getDataFromServer()
                .subscribe(this::handleGetDataSuccess,
                        this::handleGetDataError
                );
    }

    private void handleGetDataSuccess() {
        Log.e("Presenter", "stored data: " + mDataSources.getStoredData().size());
        mView.showContent(mDataSources.getStoredData());
    }

    private void handleGetDataError(Throwable throwable) {
        Log.e("Presenter", "Api error " + throwable.toString());
        mView.showError();
    }
}
