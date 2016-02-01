package com.two_two.checkreaction.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Dmitry Borodin on 27.01.2016.
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
