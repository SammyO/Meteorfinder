package com.oddhov.meteorfinder.data.local;

import com.oddhov.meteorfinder.data.models.realm.DummyData;
import com.oddhov.meteorfinder.data.models.realm.Meteor;

/**
 * Created by sammy on 06/09/17.
 */

public interface LocalDataSource {
    void saveMeteor(Meteor meteor);

    DummyData getData();
}
