package com.two_two.checkreaction.models;

/**
 * Created by Dmitry Borodin on 1/3/2016.
 * Test type and attempts amount
 */
public enum TestType {

    SIMPLE_TEST(1),
    COMPLEX_TEST(6);

    public static final String TAG = "TestType";
    private final int attempts;
    TestType(int attempts) {
        this.attempts = attempts;
    }

    public int getMaxAttempts() {
        return attempts;
    }

}
