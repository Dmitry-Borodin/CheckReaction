package com.two_two.checkreaction.models;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.firebase.client.Firebase;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.two_two.checkreaction.models.game.ParseStoredResult;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this.getApplicationContext(), new Crashlytics());
        Firebase.setAndroidContext(this.getApplicationContext());
    }


}
