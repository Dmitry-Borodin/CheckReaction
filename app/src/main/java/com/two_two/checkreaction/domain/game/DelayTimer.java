package com.two_two.checkreaction.domain.game;

import android.os.Handler;

import java.util.Random;

/**
 * Created by Dmitry Borodin on 1/4/2016.
 * Manually configured delay timer.
 * Runs provided code after random delay with preconfigured settings.
 */
public final class DelayTimer {

    private static final int MIN_DELAY = 500;
    private static final int RANDOM_DELAY1 = 5000;
    private static final int RANDOM_DELAY2 = 1000;
    private final Random mRandom = new Random();

    public void runDelayed(final Delayed delayed) {
        new Handler().postDelayed(delayed::delayedCode, getRandomTime());
    }

    private int getRandomTime() {
        //two randoms to more nice delay distribution - less possible min delay
        return mRandom.nextInt(RANDOM_DELAY1) + mRandom.nextInt(RANDOM_DELAY2) + MIN_DELAY;
    }

    public interface Delayed {
        void delayedCode();
    }
}
