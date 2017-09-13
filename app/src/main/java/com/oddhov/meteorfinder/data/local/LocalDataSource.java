package com.oddhov.meteorfinder.data.local;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

/**
 * Created by sammy on 06/09/17.
 */

public interface LocalDataSource {
    void saveData(DummyData dummyData);

    DummyData getData();
}
