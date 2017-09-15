package com.oddhov.meteorfinder.data.local;

import com.oddhov.meteorfinder.data.models.Meteor;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by sammy on 06/09/17.
 */

public class LocalDataSourceImpl implements LocalDataSource {
    //region Fields
    private Realm mRealm;
    //endregion

    @Inject
    public LocalDataSourceImpl(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public boolean hasLocalData() {
        mRealm.refresh();
        return !mRealm.where(Meteor.class).findAll().isEmpty();
    }

    @Override
    public void saveMeteor(Meteor meteor) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(x -> realm.copyToRealmOrUpdate(meteor));
    }

    @Override
    public List<Meteor> getData() {
        mRealm.refresh();
        RealmResults<Meteor> meteors = mRealm.where(Meteor.class).findAll();
        return mRealm.copyFromRealm(meteors);
    }
}
