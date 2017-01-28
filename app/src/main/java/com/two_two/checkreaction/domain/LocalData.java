package com.two_two.checkreaction.domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Provide local persistance.
 * This class will be provided by Application. In this small project testing disadvantage is not
 * significant. DI not used for demo purposes.
 */
public final class LocalData {

    private static final String USERNAME = "username";
    private static final String DEFAULT_USERNAME = "gamer";

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public LocalData(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setUsername(String username) {
        mEditor.putString(USERNAME, username).apply();
    }

    public String getUsername() {
        return mPrefs.getString(USERNAME, DEFAULT_USERNAME);
    }

}
