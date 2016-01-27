package com.two_two.checkreaction.models.game;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */

@ParseClassName("ParseTestResults")
public class ParseTestResult extends ParseObject {

    private static final String USERNAME = "username";
    private static final String AVERAGE = "average";
    private static final String MEDIAN = "median";
    private static final String TESTTYPE = "testType";
    private static final Integer VERSION = 1;

    public ParseTestResult(TestResult testResult) {

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
        return getString(USERNAME);
    }

    private void setTesttypeString(String value) {
        put(USERNAME, value);
    }


//    public static ParseQuery<ParseTestResult> getQuery() {
//        return ParseQuery.getQuery(ParseTestResult.class);
//    }
}
