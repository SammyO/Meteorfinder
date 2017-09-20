package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.utils.Constants;
import com.oddhov.meteorfinder.utils.QueryUtils;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by sammy on 06/09/17.
 */

public class NetworkDataSourceImpl implements NetworkDataSource {
    private ApiService mApiService;
    private LocalDataSource mLocalDataSource;

    @Inject
    public NetworkDataSourceImpl(ApiService apiService, LocalDataSource localDataSource) {
        this.mApiService = apiService;
        this.mLocalDataSource = localDataSource;
    }

    @Override
    public Completable getData() {
        return mApiService.getMeteors(
                QueryUtils.getEncodedWhereQuery(Constants.YEAR, Constants.GREATER_OR_EQUALS,
                        Constants.TIMESTAMP_2011),
                Constants.FELL,
                QueryUtils.getEncodedOrderQuery(Constants.MASS))
            .flatMapIterable(meteors -> meteors)
            .flatMapCompletable(meteor -> {
                mLocalDataSource.saveMeteor(meteor);
                return Completable.complete();
            });
    }
}
