package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by sammy on 06/09/17.
 */

public class NetworkDataSourceImpl implements NetworkDataSource {
    //region Fields
    private ApiService mApiService;
    //endregion

    @Inject
    public NetworkDataSourceImpl(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Single<DummyData> getData() {
        return mApiService.getData();
    }
}
