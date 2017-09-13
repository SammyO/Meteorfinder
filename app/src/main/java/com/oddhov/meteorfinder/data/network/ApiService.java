package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by sammy on 06/09/17.
 */

public interface ApiService {
    int DEFAULT_TIMEOUT = 10;
    String BASE_URL = "";

    @GET("")
    Single<DummyData> getData();
}
