package com.oddhov.meteorfinder.utils.interceptors;

import com.oddhov.meteorfinder.data.network.ApiService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sammy on 20/09/17.
 */

public class AuthorizationInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        requestBuilder.addHeader(
                ApiService.HEADER_X_APP_TOKEN,
                ApiService.X_APP_TOKEN
        );

        return chain.proceed(requestBuilder.build());
    }
}
