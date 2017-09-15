package com.oddhov.meteorfinder.meteor_detail;

import com.oddhov.meteorfinder.base.BasePresenter;
import com.oddhov.meteorfinder.utils.ScreenTransition;

/**
 * Created by sammy on 13/09/17.
 */

public interface MeteorDetailContract {
    interface View {
        void closeActivityWithTransition(ScreenTransition screenTransition);
    }

    interface Presenter extends BasePresenter {
        void onBackPressed();
    }
}
