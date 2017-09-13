package com.oddhov.meteorfinder.meteorfinder;

/**
 * Created by sammy on 13/09/17.
 */

public interface MeteorFinderContract {
    interface View {

    }

    interface Presenter {
        void subscribe();

        void updateData(boolean isRefreshing);

        void unSubscribe();
    }
}
