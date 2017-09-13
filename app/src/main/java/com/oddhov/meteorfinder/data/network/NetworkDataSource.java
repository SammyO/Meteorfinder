package com.oddhov.meteorfinder.data.network;

import io.reactivex.Completable;

/**
 * Created by sammy on 06/09/17.
 */

public interface NetworkDataSource {
    Completable getData();
}
