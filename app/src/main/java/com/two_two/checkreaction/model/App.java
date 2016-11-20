package com.two_two.checkreaction.model;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.firebase.client.Firebase;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
public class App extends Application {

    private static App sInstance;
    private LocalData mLocalData;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Fabric.with(this.getApplicationContext(), new Crashlytics());
        Firebase.setAndroidContext(this.getApplicationContext());
        mLocalData = new LocalData(this.getApplicationContext());
    }

    public static App getInstance() {
        return sInstance;
    }

    public LocalData getLocalData() {
        return mLocalData;
    }
}
