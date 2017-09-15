package com.oddhov.meteorfinder.meteor_detail.presentation;

import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.meteor_detail.MeteorDetailContract;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import javax.inject.Inject;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorDetailPresenter implements MeteorDetailContract.Presenter {
    private DataSources mDataSources;
    private MeteorDetailContract.View mView;

    @Inject
    public MeteorDetailPresenter(DataSources dataSources, MeteorDetailContract.View view) {
        this.mDataSources = dataSources;
        this.mView = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onBackPressed() {
        mView.closeActivityWithTransition(ScreenTransition.BACK_SLIDING_SLIDING);
    }
}
