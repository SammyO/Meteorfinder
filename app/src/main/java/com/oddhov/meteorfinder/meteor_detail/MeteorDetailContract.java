package com.oddhov.meteorfinder.meteor_detail;

import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.oddhov.meteorfinder.base.BasePresenter;
import com.oddhov.meteorfinder.utils.ScreenTransition;

/**
 * Created by sammy on 13/09/17.
 */

public interface MeteorDetailContract {
    interface View {
        void setScreenTitle(String title);

        void closeActivityWithTransition(ScreenTransition screenTransition);

        void moveCamera(LatLng latLng);

        void dropMarker(LatLng latLng);

        void setName(String name);

        void setYear(String year);

        void setMass(Integer format, String mass);

        void setClassType(Integer format, String classType);
    }

    interface Presenter extends BasePresenter {
        void setIntent(Intent intent);

        void onBackPressed();
    }
}
