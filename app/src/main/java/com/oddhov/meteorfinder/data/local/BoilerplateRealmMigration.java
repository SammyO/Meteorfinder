package com.oddhov.meteorfinder.data.local;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * Created by sammy on 06/09/17.
 */

public class BoilerplateRealmMigration implements RealmMigration {
    public static final int DATABASE_VERSION = 1;

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
