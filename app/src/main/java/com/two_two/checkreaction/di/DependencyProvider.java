package com.two_two.checkreaction.di;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.two_two.checkreaction.domain.science.ScienceTest;
import com.two_two.checkreaction.domain.science.colors.ColourProvider;
import com.two_two.checkreaction.domain.science.colors.ColourShaker;
import com.two_two.checkreaction.models.App;
import com.two_two.checkreaction.utils.ColorGenerator;

/**
 * @author Dmitry Borodin on 2017-01-29.
 *
 * Provides singletons for app
 */

public class DependencyProvider {

    private static ScienceTest scienceTest;
    @SuppressLint("StaticFieldLeak") //this is App context - it's singleton
    private static ColourProvider colourProvider;
    @SuppressLint("StaticFieldLeak") //this is App context - it's singleton
    private static ColorGenerator colorGenerator;

    @NonNull
    public static ScienceTest getScienceTest() {
        if (scienceTest == null) {
            scienceTest = new ScienceTest(new ColourShaker());
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

    @NonNull
    public static ColorGenerator getColourGenerator() {
        if (colorGenerator == null) {
            colorGenerator = new ColorGenerator(getAppContext());
        }
        return colorGenerator;
    }

    public static Context getAppContext() {
        return App.getInstance();
    }
}
