package com.oddhov.meteorfinder.meteorfinder;

import android.content.Context;
import android.content.Intent;

import com.oddhov.meteorfinder.base.BasePresenter;
import com.oddhov.meteorfinder.base.BaseView;
import com.oddhov.meteorfinder.meteorfinder.view.MeteorAdapter;
import com.oddhov.meteorfinder.utils.ScreenTransition;

/**
 * Created by sammy on 13/09/17.
 */

public interface MeteorFinderContract {
    interface View extends BaseView {
        Context getContext();

        void setAdapter(MeteorAdapter adapter);

        void showLoading();

        void showContent();

        void showError();

        void showEmpty();

        void startActivityWithTransition(Intent intent, ScreenTransition screenTransition);
    }

    interface Presenter<T extends BaseView> extends BasePresenter<T> {
        void onRefresh();
    }
}
