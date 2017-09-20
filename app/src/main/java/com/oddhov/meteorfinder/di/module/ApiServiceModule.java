package com.oddhov.meteorfinder.di.module;

import com.google.gson.GsonBuilder;
import com.oddhov.meteorfinder.BuildConfig;
import com.oddhov.meteorfinder.data.network.ApiService;
import com.oddhov.meteorfinder.utils.interceptors.AuthorizationInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sammy on 07/09/17.
 */

@Module
public class ApiServiceModule {

    public ApiServiceModule() {}

    @Singleton
    @Provides
    public ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                     GsonConverterFactory gsonConverterFactory,
                                     OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(ApiService.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient);
        return builder.build();
    }

    @Singleton
    @Provides
    public RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create(
                new GsonBuilder().create()
        );
    }

    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                                             AuthorizationInterceptor authorizationInterceptor) {
        if (BuildConfig.DEBUG) {
            return new OkHttpClient.Builder()
                    .addInterceptor(authorizationInterceptor)
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(ApiService.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .connectTimeout(ApiService.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    AuthorizationInterceptor provideAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }
}
