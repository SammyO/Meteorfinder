package com.oddhov.meteorfinder.base;

/**
 * Created by sammy on 15/09/17.
 */

public interface BasePresenter<T extends BaseView>{
    void initialize();

    void unSubscribe();
}
