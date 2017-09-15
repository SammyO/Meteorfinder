package com.oddhov.meteorfinder.data.models;

/**
 * Created by sammy on 13/09/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Meteor extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("fall")
    @Expose
    private String mFall;
    @SerializedName("mass")
    @Expose
    private String mMass;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("nametype")
    @Expose
    private String mNameType;
    @SerializedName("recclass")
    @Expose
    private String mClass;
    @SerializedName("reclat")
    @Expose
    private String mLatitude;
    @SerializedName("reclong")
    @Expose
    private String mLongitude;
    @SerializedName("year")
    @Expose
    private String mYear;

    public String getFall() {
        return mFall;
    }

    public void setFall(String fall) {
        this.mFall = fall;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getMass() {
        return mMass;
    }

    public void setMass(String mass) {
        this.mMass = mass;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getNametype() {
        return mNameType;
    }

    public void setNametype(String nameType) {
        this.mNameType = nameType;
    }

    public String getRecclass() {
        return mClass;
    }

    public void setRecclass(String recclass) {
        this.mClass = recclass;
    }

    public String getReclat() {
        return mLatitude;
    }

    public void setReclat(String latitude) {
        this.mLatitude = latitude;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(String longitude) {
        this.mLongitude = longitude;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String year) {
        this.mYear = year;
    }

}
