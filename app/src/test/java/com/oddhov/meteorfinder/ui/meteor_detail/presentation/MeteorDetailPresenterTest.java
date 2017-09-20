package com.oddhov.meteorfinder.ui.meteor_detail.presentation;

import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;
import com.oddhov.meteorfinder.R;
import com.oddhov.meteorfinder.base.BaseTestCase;
import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.ui.meteor_detail.MeteorDetailContract;
import com.oddhov.meteorfinder.utils.Constants;
import com.oddhov.meteorfinder.utils.DateUtils;
import com.oddhov.meteorfinder.utils.LocationUtils;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by sammy on 18/09/17.
 */

public class MeteorDetailPresenterTest extends BaseTestCase {
    private MeteorDetailPresenter mPresenter;

    @Mock
    DataSources mDataSources;
    @Mock
    MeteorDetailContract.View mView;
    @Mock
    Intent mIntent;
    @Mock
    DateUtils mDateUtils;
    @Mock
    LocationUtils mLocationUtils;

    @Captor
    ArgumentCaptor<LatLng> mLatLang;

    /**
     * Tests are named according to "action" - "given" - "should".
     * - "Action" corresponds with the name of the method under testing.
     * - "Given" corresponds with any parameters that are passed to the method, or that are set
     *    before the method is called
     * - "Should" corresponds with the expected output/action that the method in this test produces.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new MeteorDetailPresenter(mView, mDataSources, mDateUtils, mLocationUtils);
    }

    @Test
    public void initializeShouldSetTitleSetMapAndSetUi() {
        // GIVEN
        String meteorId = "1";
        doReturn(meteorId).when(mIntent).getStringExtra(Constants.METEOR_ID);
        setFieldValueThroughReflection(mPresenter, "mIntent", mIntent);

        String meteorName = "MeteorName";
        String meteorYear = "2012-01-01T00:00:00.000";
        String mass = "1000";
        String classType = "C4";
        double latitude = 123;
        double longitude = 123;
        Meteor meteor = new Meteor();
        meteor.setName(meteorName);
        meteor.setLatitude(latitude);
        meteor.setLongitude(longitude);
        meteor.setYear(meteorYear);
        meteor.setMass(mass);
        meteor.setClassType(classType);
        doReturn(meteor).when(mDataSources).getMeteorWithId(meteorId);

        double latDegrees = 12;
        double longDegrees = 21;
        doReturn(latDegrees).when(mLocationUtils).getLatitudeInDegreesFromLatitude(latitude);
        doReturn(longDegrees).when(mLocationUtils).getLongitudeInDegreesFromLongitude(longitude);

        Date date = new Date();
        doReturn(date).when(mDateUtils).getYearFromDateString(meteorYear);
        String year = "2012";
        doReturn(year).when(mDateUtils).getYearFromDate(date);

        // ACTION
        mPresenter.initialize();

        // SHOULD
        verify(mView).setScreenTitle(meteorName);
        verify(mView).moveCamera(mLatLang.capture());
        assertEquals(latDegrees, mLatLang.getValue().latitude);
        assertEquals(longDegrees, mLatLang.getValue().longitude);
        verify(mView).setName(meteorName);
        verify(mView).setYear(year);
        verify(mView).setMass(R.string.meteor_mass, mass);
        verify(mView).setClassType(R.string.meteor_class, classType);
    }

    @Test
    public void onBackPressedShouldCloseActivityWithTransition() {
        // GIVEN
        setFieldValueThroughReflection(mPresenter, "mView", mView);

        // ACTION
        mPresenter.onBackPressed();

        // SHOULD
        verify(mView).closeActivityWithTransition(ScreenTransition.BACK_SLIDING_SLIDING);
    }
}
