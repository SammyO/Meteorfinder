package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by sammy on 06/09/17.
 */

public interface DataSources {
    DummyData getStoredData();

    Observable<DummyData> getDataFromServer();

    void storeData(DummyData dummyData);
}
