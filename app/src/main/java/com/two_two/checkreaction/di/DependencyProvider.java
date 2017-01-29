package com.two_two.checkreaction.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.two_two.checkreaction.domain.science.ScienceTest;
import com.two_two.checkreaction.domain.science.colors.ColourProvider;
import com.two_two.checkreaction.models.App;

/**
 * @author Dmitry Borodin on 2017-01-29.
 *
 * Provides singletons for app
 */

public class DependencyProvider {

    private static ScienceTest scienceTest;
    private static ColourProvider colourProvider;

    @NonNull
    public static ScienceTest getScienceTest() {
        if (scienceTest == null) {
            scienceTest = new ScienceTest(getColourProvider());
        }
        return scienceTest;
    }

    @NonNull
    public static ColourProvider getColourProvider() {
        if (colourProvider == null) {
            colourProvider = new ColourProvider(getAppContext());
        }
        return colourProvider;
    }

    public static Context getAppContext() {
        return App.getInstance();
    }
}
