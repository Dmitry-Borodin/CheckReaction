package com.two_two.checkreaction.models.game;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.two_two.checkreaction.models.LocalStorage;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */

@ParseClassName("ParseTestResults")
public class ParseStoredResult extends ParseObject {

    private static final String USERNAME = "username";
    private static final String AVERAGE = "average";
    private static final String MEDIAN = "median";
    private static final String TESTTYPE = "testType";
    private static final String VERSION = "version";
    private static final Integer VERSION_VALUE = 1;

    public ParseStoredResult(TestResult testResult) {

        if (testResult.isFailed()) throw new RuntimeException("Can't store failed results");

        setVersion();
        setUserName(LocalStorage.getUsername());
        setAverage(testResult.getAverage());
        setMedian(testResult.getMedian());
        setTesttypeString(testResult.getTestType().toString());
    }

    //*******************************************************
    // Section: methods
    //*******************************************************

    public String getUsername() {
        return getString(USERNAME);
    }

    private void setUserName(String value) {
        put(USERNAME, value);
    }

    public Long getAverage() {
        return getLong(AVERAGE);
    }

    private void setAverage(Long value) {
        put(AVERAGE, value);
    }
    public Long getMedian() {
        return getLong(MEDIAN);
    }

    private void setMedian(Long value) {
        put(MEDIAN, value);
    }

    public String getTesttypeString() {
        return getString(TESTTYPE);
    }

    private void setTesttypeString(String value) {
        put(TESTTYPE, value);
    }

    public int getVersion() {
        return getInt(VERSION);
    }

    private void setVersion() {
        put(VERSION, VERSION_VALUE);
    }


//    public static ParseQuery<ParseStoredResult> getQuery() {
//        return ParseQuery.getQuery(ParseStoredResult.class);
//    }
}
