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
        Log.e("Presenter", "getting data from server");
        mDataSources.getDataFromServer()
                .subscribe(x -> Log.e("Presenter", "Api success"),
                        e -> Log.e("Presenter", "Api error" + e.getMessage())
                );
    }

    @Override
    public void unSubscribe() {

    }
}
