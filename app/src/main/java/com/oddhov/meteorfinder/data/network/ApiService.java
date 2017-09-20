package com.oddhov.meteorfinder.data.network;

import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.utils.Constants;

import java.util.List;

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

    @GET("y77d-th95.json")
    Observable<List<Meteor>> getMeteorsThatFell(
        @Query(Constants.WHERE_CLAUSE) String whereQuery,
        @Query(Constants.FALL) String fall,
        @Query(Constants.ORDER_CLAUSE) String orderQuery
    );

    @GET("y77d-th95.json")
    Observable<List<Meteor>> getAllMeteors(
            @Query(Constants.WHERE_CLAUSE) String whereQuery,
            @Query(Constants.ORDER_CLAUSE) String orderQuery
    );
}
