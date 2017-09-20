package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.models.Meteor;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by sammy on 06/09/17.
 */

public interface DataSources {
    boolean hasLocalData();

    List<Meteor> getAllMeteors();

    Meteor getMeteorWithId(String id);

    Completable getAllMeteorsFromServer();

    Completable getAllFallenMeteorsFromServer();
}
