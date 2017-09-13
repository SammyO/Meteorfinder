package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.realm.DummyData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by sammy on 06/09/17.
 */

public interface ApiService {
    int DEFAULT_TIMEOUT = 10;
    String BASE_URL = "https://data.nasa.gov/resource/";
    String X_APP_TOKEN = "ToGfplYVyilyes6Dbx1CVmVR5";

    String HEADER_X_APP_TOKEN = "X-App-Token";
    String PARAM_YEAR = "year";
    String PARAM_FALL = "fall";

    @GET("y77d-th95.json")
    Observable<DummyData> getMeteors(
        @Header(HEADER_X_APP_TOKEN) String xAppToken,
        @Query(PARAM_YEAR) String year,
        @Query(PARAM_FALL) String fall
    );
}
