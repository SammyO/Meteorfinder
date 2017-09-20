package com.oddhov.meteorfinder;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.oddhov.meteorfinder.ui.meteorfinder.view.MeteorFinderActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.oddhov.meteorfinder.util.CustomMatchers.withRecyclerView;

/**
 * Created by sammy on 19/09/17.
 */

public class MeteorFinderActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MeteorFinderActivity> activityTestRule =
            new ActivityTestRule<>(MeteorFinderActivity.class, false, false);

    @Test
    public void startActivityAndClickOnListItemShouldShowMapAndMeteorDetailCard() throws Exception {
        startActivity();

        int position = 1;
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(position));
        onView(withRecyclerView(R.id.recyclerView).atPosition(position))
                .perform(click());
        onView(withId(R.id.mapView)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.mapCard)).check(matches(isDisplayed()));
    }


    private void startActivity() {
        Intent intent = new Intent();
        activityTestRule.launchActivity(intent);
    }

}
