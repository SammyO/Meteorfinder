package com.oddhov.meteorfinder.meteorfinder.presentation;

import android.content.Intent;

import com.oddhov.meteorfinder.base.BaseTestCase;
import com.oddhov.meteorfinder.data.DataSources;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.meteorfinder.MeteorFinderContract;
import com.oddhov.meteorfinder.meteorfinder.view.MeteorAdapter;
import com.oddhov.meteorfinder.utils.DateUtils;
import com.oddhov.meteorfinder.utils.ScreenTransition;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by sammy on 18/09/17.
 */

public class MeteorFinderPresenterTest extends BaseTestCase {
    private MeteorFinderPresenter mPresenter;

    @Mock
    DataSources mDataSources;
    @Mock
    MeteorFinderContract.View mView;
    @Mock
    MeteorAdapter mAdapter;
    @Mock
    DateUtils mDateUtils;

    @Captor
    ArgumentCaptor<Intent> mIntentCaptor;

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

        mPresenter = new MeteorFinderPresenter(mView, mDataSources, mAdapter);
    }

    @Test
    public void initializeWithLocalDataShouldSetAdapterSetContentAndFetchData() {
        // GIVEN

        // Reflection is not ideal, but I want to test only this one method. Therefore I'm setting
        // the state of the method manually using reflection, instead of calling other methods of
        // this presenter like 'setIntent'
        doReturn(true).when(mDataSources).hasLocalData();

        List<Meteor> meteors = new ArrayList<>();
        doReturn(meteors).when(mDataSources).getAllMeteors();

        Completable completable = mock(Completable.class);
        doReturn(completable).when(mDataSources).getDataFromServer();

        // ACTION
        mPresenter.initialize();

        // SHOULD
        verify(mView).setAdapter(mAdapter);
        verify(mAdapter).setMeteorItemOnClickListener(mPresenter);
        verify(mAdapter).setData(meteors);
        verify(mView).showContent();
        verify(mDataSources).getDataFromServer();
    }

    @Test
    public void initializeWithoutLocalDataShouldSetAdapterSetLoadingViewAndFetchData() {
        // GIVEN
        doReturn(false).when(mDataSources).hasLocalData();

        Completable completable = mock(Completable.class);
        doReturn(completable).when(mDataSources).getDataFromServer();

        // ACTION
        mPresenter.initialize();

        // SHOULD
        verify(mView).setAdapter(mAdapter);
        verify(mAdapter).setMeteorItemOnClickListener(mPresenter);
        verify(mView).showLoading();
    }

    @Test
    public void onRefreshShouldFetchData() {
        // GIVEN
        Completable completable = mock(Completable.class);
        doReturn(completable).when(mDataSources).getDataFromServer();

        // ACTION
        mPresenter.onRefresh();

        // SHOULD
        verify(mDataSources).getDataFromServer();
    }

    @Test
    public void onItemClickShouldStartNewActivityForMeteorId() {
        // GIVEN
        String meteorId = "1";

        // ACTION
        mPresenter.onItemClick(meteorId);

        // SHOULD
        verify(mView).startActivityWithTransition(any(Intent.class), eq(ScreenTransition.NEXT_SLIDING_SLIDING));
    }

    @Test
    public void updateDataWithSuccessShouldShowContent() {
        // GIVEN
        Completable completable = Completable.complete();
        doReturn(completable).when(mDataSources).getDataFromServer();

        // ACTION
        mPresenter.updateData();

        // SHOULD
        verify(mAdapter).setData(anyList());
        verify(mView).showContent();
        verify(mView, never()).showError();
    }

    @Test
    public void updateDataWithErrorShouldShowContent() {
        // GIVEN
        Completable completable = Completable.error(new Throwable());
        doReturn(completable).when(mDataSources).getDataFromServer();

        // ACTION
        mPresenter.updateData();

        // SHOULD
        verify(mAdapter, never()).setData(anyList());
        verify(mView).showError();
    }

}
