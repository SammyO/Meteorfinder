package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.data.models.realm.DummyData;
import com.oddhov.meteorfinder.data.network.NetworkDataSource;

import io.reactivex.Observable;
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
    public DummyData getStoredData() {
        return mLocalDataSource.getData();
    }

    @Override
    public Observable<DummyData> getDataFromServer() {
        return mNetworkDataSource.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void storeData(DummyData dummyData) {

    }
}
