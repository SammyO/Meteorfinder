package com.oddhov.meteorfinder.meteorfinder.presentation;

import android.content.Intent;

import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorDetailActivity;
import com.oddhov.meteorfinder.meteor_detail.view.MeteorItemOnClickListener;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;
import com.oddhov.meteorfinder.meteorfinder.view.MeteorAdapter;
import com.oddhov.meteorfinder.utils.Constants;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorFinderPresenter implements MeteorFinderContract.Presenter<MeteorFinderContract.View>, MeteorItemOnClickListener {
    private CompositeDisposable mSubscriptions = new CompositeDisposable();
    private DataSources mDataSources;
    private MeteorFinderContract.View mView;
    private MeteorAdapter mMeteorAdapter;

    @Inject
    public MeteorFinderPresenter(MeteorFinderContract.View view, DataSources dataSources, MeteorAdapter meteorAdapter) {
        this.mView = view;
        this.mDataSources = dataSources;
        this.mMeteorAdapter = meteorAdapter;
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
        updateData();
    }

    @Override
    public void unSubscribe() {
        if (mSubscriptions != null && !mSubscriptions.isDisposed()) {
            mSubscriptions.dispose();
        }
        this.mView = null;
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


    protected void updateData() {
        mDataSources.getDataFromServer()
                .subscribe(this::showContent,
                        e -> handleGetDataError()
                );
    }

    private void showContent() {
        mMeteorAdapter.setData(mDataSources.getAllMeteors());
        mView.showContent();
    }

    private void handleGetDataError() {
        mView.showError();
    }
}
