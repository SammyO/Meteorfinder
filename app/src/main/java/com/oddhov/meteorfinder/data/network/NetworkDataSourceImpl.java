package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import javax.inject.Inject;

import io.reactivex.Observable;
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
    public Observable<DummyData> getData() {
        return mApiService.getMeteors(
                ApiService.X_APP_TOKEN,
                "2011-01-01T00:00:00.000",
                "Fell"
        );
    }
}
