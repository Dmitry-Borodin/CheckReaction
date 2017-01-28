package com.two_two.checkreaction.models.game;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * POJO for storing results of complex test in firebase
 */
public class FirebaseComplexResult implements Parcelable {

    public static final String MEDIAN = "median";
    public static final String TAG = "FirebaseComplexResult";

    //breaking AOSP naming policy for more nice getters names since Firebase have strict
    // requirements about that.
    private long average;
    private long median;
    private String username;
    //doesn't participate in equials because it will fill up only in firebase cloud.
    private long timestamp;

    public FirebaseComplexResult(long average, long median, String username) {
        this.average = average;
        this.median = median;
        this.username = username;
    }

    /*
    * This constructor for Firebase use. There is no reason to use it manually.
     */
    @Deprecated
    public FirebaseComplexResult() {
    }

    //*******************************************************
    // Section: Methods
    //*******************************************************

    public long getAverage() {
        return average;
    }

    public long getMedian() {
        return median;
    }

    public String getUsername() {
        return username;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirebaseComplexResult that = (FirebaseComplexResult) o;

        if (average != that.average) return false;
        if (median != that.median) return false;
        return username.equals(that.username);

    }

    @Override
    public int hashCode() {
        int result = (int) (average ^ (average >>> 32));
        result = 31 * result + (int) (median ^ (median >>> 32));
        return result;
    }

    //*******************************************************
    // Section: Parcelable
    //*******************************************************


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.average);
        dest.writeLong(this.median);
        dest.writeString(this.username);
        dest.writeLong(this.timestamp);
    }

    protected FirebaseComplexResult(Parcel in) {
        this.average = in.readLong();
        this.median = in.readLong();
        this.username = in.readString();
        this.timestamp = in.readLong();
    }

    public static final Creator<FirebaseComplexResult> CREATOR = new Creator<FirebaseComplexResult>() {
        public FirebaseComplexResult createFromParcel(Parcel source) {
            return new FirebaseComplexResult(source);
        }

        public FirebaseComplexResult[] newArray(int size) {
            return new FirebaseComplexResult[size];
        }
    };
}
