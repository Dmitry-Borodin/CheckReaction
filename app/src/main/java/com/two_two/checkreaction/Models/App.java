package com.two_two.checkreaction.models;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        parseInitialise();
    }

    private void parseInitialise() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        // Add your initialization code here
        Parse.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParseUser.enableAutomaticUser();
    }
}
