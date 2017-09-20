package com.oddhov.meteorfinder.data;

import com.oddhov.meteorfinder.data.local.LocalDataSource;
import com.oddhov.meteorfinder.data.models.Meteor;
import com.oddhov.meteorfinder.data.network.NetworkDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

/**
 * Created by sammy on 19/09/17.
 */

public class DataSourcesImplTest {
    @Mock
    DataSources mDataSources;
    @Mock
    LocalDataSource mLocalDataSource;
    @Mock
    NetworkDataSource mNetworkDataSource;

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

        mDataSources = new DataSourcesImpl(mLocalDataSource, mNetworkDataSource);
    }

    @Test
    public void hasLocalDataWithLocalDataPresentShouldReturnTrue() {
        // GIVEN
        doReturn(true).when(mLocalDataSource).hasLocalData();

        // ACTION
        boolean verify = mDataSources.hasLocalData();

        // SHOULD
        assertTrue(verify);
    }

    @Test
    public void getAllMeteorsWithMeteorsInLocalDataSourceShouldReturnList() {
        // GIVEN
        Meteor meteor1 = new Meteor();
        Meteor meteor2 = new Meteor();
        List<Meteor> meteors = Arrays.asList(meteor1, meteor2);

        doReturn(meteors).when(mLocalDataSource).getAllMeteors();

        // ACTION
        List<Meteor> result = mDataSources.getAllMeteors();

        // SHOULD
        assertEquals(meteors, result);
    }

    @Test
    public void getMeteorWithIdShouldReturnLocalDataStorageGetMeteorWithId() {
        // GIVEN
        String id = "1";
        Meteor meteor = new Meteor();
        doReturn(meteor).when(mLocalDataSource).getMeteorWithId(id);

        // ACTION
        Meteor result = mDataSources.getMeteorWithId(id);

        // SHOULD
        assertEquals(meteor, result);
    }

    @Test
    public void getDataFromServerShouldReturnNetworkDataSourceGetDataFromServer() {
        // GIVEN
        doReturn(Completable.complete()).when(mNetworkDataSource).getAllMeteors();

        // ACTION
        TestObserver testObserver = mDataSources.getAllMeteorsFromServer().test();

        // SHOULD
        testObserver.assertNoErrors();
    }
}
