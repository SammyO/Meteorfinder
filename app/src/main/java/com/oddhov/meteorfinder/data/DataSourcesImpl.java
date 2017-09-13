package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.data.models.realm.DummyData;
import com.oddhov.meteorfinder.data.network.NetworkDataSource;

import io.reactivex.Single;

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
    public DummyData getStoredData() {
        return mLocalDataSource.getData();
    }

    @Override
    public Single<DummyData> getDataFromServer() {
        return mNetworkDataSource.getData();
    }

    @Override
    public void storeData(DummyData dummyData) {

    }
}
