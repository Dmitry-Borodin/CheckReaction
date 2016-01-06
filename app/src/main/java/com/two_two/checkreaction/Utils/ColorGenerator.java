package com.two_two.checkreaction.utils;

import android.content.Context;
import android.graphics.Color;

import com.two_two.checkreaction.R;

/**
 * Returns background colors.
 * This class made up quickly and should be cleaned up
 */
public class ColorGenerator {

    private int mIteration = 1;
    private Context mContext;

    public ColorGenerator(Context context) {
        this.mContext = context;
    }

    public int getNextColor() {
        int color = nextColor();
        mIteration++;
        if (mIteration > 6) mIteration = 1;
        return color;
    }

    private int nextColor(){
        switch (mIteration){
            case 1:
                return mContext.getResources().getColor(R.color.test1);
            case 2:
                return mContext.getResources().getColor(R.color.test2);
            case 3:
                return mContext.getResources().getColor(R.color.test3);
            case 4:
                return mContext.getResources().getColor(R.color.test4);
            case 5:
                return mContext.getResources().getColor(R.color.test5);
            case 6:
                return mContext.getResources().getColor(R.color.test6);
            default:
                return Color.BLACK;
        }
    }
}
