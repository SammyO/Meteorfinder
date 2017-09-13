package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by sammy on 06/09/17.
 */

public interface ApiService {
    int DEFAULT_TIMEOUT = 10;
    String BASE_URL = "https://data.nasa.gov/resource/y77d-th95.json";
    String X_APP_TOKEN = "ToGfplYVyilyes6Dbx1CVmVR5";

    @GET("")
    Single<DummyData> getData();
}
