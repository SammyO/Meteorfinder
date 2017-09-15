package com.oddhov.meteorfinder.meteorfinder;

import com.oddhov.meteorfinder.data.models.Meteor;

import java.util.List;

/**
 * Created by sammy on 13/09/17.
 */

public interface MeteorFinderContract {
    interface View {
        void showLoading();

        void showContent(List<Meteor> meteors);

        void showError();

        void showEmpty();
    }

    interface Presenter {
        void subscribe();

        void initialize();

        void unSubscribe();

        void onRefresh();
    }
}
