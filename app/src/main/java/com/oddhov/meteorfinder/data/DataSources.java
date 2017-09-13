package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.models.realm.DummyData;
import com.oddhov.meteorfinder.data.models.realm.Meteor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by sammy on 06/09/17.
 */

public interface DataSources {
    DummyData getStoredData();

    Completable getDataFromServer();

    void storeData(DummyData dummyData);
}
