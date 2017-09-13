package com.oddhov.meteorfinder.data.models.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sammy on 06/09/17.
 */

public class DummyData extends RealmObject {
    @PrimaryKey
    private long mId;
    private String mName;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
