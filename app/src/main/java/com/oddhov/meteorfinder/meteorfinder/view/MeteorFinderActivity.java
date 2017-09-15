package com.oddhov.meteorfinder.meteorfinder.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import com.oddhov.meteorfinder.MeteorFinderApp;
import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.meteorfinder.DaggerMeteorFinderComponent;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;
import com.oddhov.meteorfinder.meteorfinder.presentation.MeteorFinderModule;
import com.oddhov.meteorfinder.meteorfinder.presentation.MeteorFinderPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sammy on 13/09/17.
 */

public class MeteorFinderActivity extends AppCompatActivity implements MeteorFinderContract.View, SwipeRefreshLayout.OnRefreshListener {
    //region Static Fields
    private static final int POSITION_LIST = 0;
    private static final int POSITION_LOADING = 1;
    private static final int POSITION_EMPTY = 2;
    private static final int POSITION_ERROR = 3;
    //endregion

    @Inject
    MeteorFinderPresenter mPresenter;

    @BindView(R.id.viewAnimator)
    ViewAnimator mViewAnimator;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MeteorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerMeteorFinderComponent.builder()
                .applicationComponent(((MeteorFinderApp) getApplication()).getApplicationComponent())
                .meteorFinderModule(new MeteorFinderModule(this))
                .build()
                .inject(this);

        setContentView(R.layout.activity_meteorfinder);
        ButterKnife.bind(this);

        setupAdapterAndRecyclerView();

        mPresenter.subscribe();
        mPresenter.initialize();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showContent(List<Meteor> cities) {
        mViewAnimator.setDisplayedChild(POSITION_LIST);
        mAdapter.setupData(cities);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        mViewAnimator.setDisplayedChild(POSITION_ERROR);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        mViewAnimator.setDisplayedChild(POSITION_LOADING);
    }

    @Override
    public void showEmpty() {
        mViewAnimator.setDisplayedChild(POSITION_EMPTY);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setupAdapterAndRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mAdapter = new MeteorAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }
}
