package com.two_two.checkreaction.models.firebase;

/**
 * POJO for storing results of complex test in firebase
 */
public class FirebaseComplexTestResult {

    //breaking AOSP naming policy for more nice getters names since Firebase have strict
    // requirements about that.
    private long average;
    private long median;
    private String username;

    public FirebaseComplexTestResult(long average, long median, String username) {
        this.average = average;
        this.median = median;
        this.username = username;
    }

    /*
    * This constructor for Firebase use. There is no reason to use it manually.
     */
    @Deprecated
    public FirebaseComplexTestResult() {
    }

    public long getAverage() {
        return average;
    }

    public long getMedian() {
        return median;
    }

    public String getUsername() {
        return username;
    }
}
