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
    public void updateData(boolean isRefreshing) {
        mDataSources.getDataFromServer()
                .subscribe(() -> {
                        // Show success view
                        Log.e("Presenter", "Api complete");
                    }, e -> {
                        // Show error view
                        Log.e("Presenter", "Api error " + e.toString());
                    }
                );
    }

    @Override
    public void unSubscribe() {

    }
}
