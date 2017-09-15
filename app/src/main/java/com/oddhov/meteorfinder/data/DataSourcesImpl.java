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
    public List<Meteor> getStoredData() {
        return mLocalDataSource.getData();
    }

    @Override
    public Completable getDataFromServer() {
        return mNetworkDataSource.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void storeData(Meteor dummyData) {

    }
}
