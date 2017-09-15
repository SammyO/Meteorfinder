package com.oddhov.meteorfinder.data.network;

import android.util.Log;

import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.utils.Constants;
import com.oddhov.meteorfinder.utils.QueryUtils;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by sammy on 06/09/17.
 */

public class NetworkDataSourceImpl implements NetworkDataSource {
    //region Fields
    private ApiService mApiService;
    private LocalDataSource mLocalDataSource;
    //endregion

    @Inject
    public NetworkDataSourceImpl(ApiService apiService, LocalDataSource localDataSource) {
        this.mApiService = apiService;
        this.mLocalDataSource = localDataSource;
    }

    @Override
    public Completable getData() {
        return mApiService.getMeteors(
                ApiService.X_APP_TOKEN,
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
