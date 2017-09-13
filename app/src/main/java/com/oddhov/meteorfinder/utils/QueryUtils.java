package com.oddhov.meteorfinder.utils;

/**
 * Created by sammy on 13/09/17.
 */

public class QueryUtils {
    public static String getEncodedWhereQuery(String field, String comparison, String value) {
        return String.format("%s %s '%s'", field, comparison, value);
    }

    public static String getEncodedOrderQuery(String value) {
        return value;
    }
}
