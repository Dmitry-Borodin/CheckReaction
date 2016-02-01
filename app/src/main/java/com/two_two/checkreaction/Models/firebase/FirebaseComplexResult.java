package com.two_two.checkreaction.models.firebase;

/**
 * POJO for storing results of complex test in firebase
 */
public class FirebaseComplexResult {

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
}
