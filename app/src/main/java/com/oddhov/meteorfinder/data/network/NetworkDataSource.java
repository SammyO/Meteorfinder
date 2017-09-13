package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import io.reactivex.Single;

/**
 * Created by sammy on 06/09/17.
 */

public interface NetworkDataSource {
    Single<DummyData> getData();
}
