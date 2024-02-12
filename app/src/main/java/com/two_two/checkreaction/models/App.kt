package com.two_two.checkreaction.models;

import android.app.Application;
import android.support.annotation.NonNull;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.BuildConfig;
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
        if (!BuildConfig.DEBUG){
            Fabric.with(this.getApplicationContext(), new Crashlytics());
        }
        mLocalData = new LocalData(this.getApplicationContext());
    }

    @NonNull
    public static App getInstance() {
        return sInstance;
    }

    public LocalData getLocalData() {
        return mLocalData;
    }
}
