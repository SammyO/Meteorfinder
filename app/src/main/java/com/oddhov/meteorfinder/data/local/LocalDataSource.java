package com.oddhov.meteorfinder.data.local;

import com.oddhov.meteorfinder.data.models.Meteor;

import java.util.List;

/**
 * Created by sammy on 06/09/17.
 */

public interface LocalDataSource {
    boolean hasLocalData();

    void saveMeteor(Meteor meteor);

    List<Meteor> getData();
}
