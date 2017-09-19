package com.oddhov.meteorfinder.util;

/**
 * Created by sammy on 19/09/17.
 */

public class CustomMatchers {
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}
