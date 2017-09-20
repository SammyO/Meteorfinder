package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.data.network.NetworkDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sammy on 06/09/17.
 */

public class DataSourcesImpl implements DataSources {
    private final LocalDataSource mLocalDataSource;
    private final NetworkDataSource mNetworkDataSource;

    public DataSourcesImpl(LocalDataSource localDataSource, NetworkDataSource networkDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mNetworkDataSource = networkDataSource;
    }

    @Override
    public boolean hasLocalData() {
        return mLocalDataSource.hasLocalData();
    }

    @Override
    public List<Meteor> getAllMeteors() {
        return mLocalDataSource.getAllMeteors();
    }

    @Override
    public Meteor getMeteorWithId(String id) {
        return mLocalDataSource.getMeteorWithId(id);
    }

    @Override
    public Completable getAllMeteorsFromServer() {
        return mNetworkDataSource.getAllMeteors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable getAllFallenMeteorsFromServer() {
        return mNetworkDataSource.getAllFallenMeteors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
