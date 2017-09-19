package com.oddhov.meteorfinder.utils;

import android.location.Location;

/**
 * Created by sammy on 19/09/17.
 */

public class LocationUtils {
    public double getLatitudeInDegreesFromLatitude(double latitude) {
        return Location.convert(Location.convert(latitude, Location.FORMAT_DEGREES));
    }
    public double getLongitudeInDegreesFromLongitude(double longitude) {
        return Location.convert(Location.convert(longitude, Location.FORMAT_DEGREES));
    }
}
