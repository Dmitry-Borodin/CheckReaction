package com.two_two.checkreaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Random;


public class TestActivity extends Activity implements View.OnTouchListener {

    Random random = new Random();
    int n = 0; //delete me later
    long Result = 0; // my time reaction
   boolean testing = false;
    long StartPoint;

    private void makeTest() {
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.BLUE);
        testing = true;
        StartPoint = System.currentTimeMillis();
        Log.d(ReactionTest.TAG, "testMethod finishing");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.GREEN);
        l1.setOnTouchListener(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                makeTest();
            }
        }, random.nextInt(5000) + random.nextInt(1000) + 500);
        Log.d(ReactionTest.TAG, "Result in onStart" + Result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (testing) {
            Result = System.currentTimeMillis() - StartPoint;
        }
        Log.d(ReactionTest.TAG, "Finish Result " + Result);
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("Result", Result);
        startActivity(intent);
        finish();
        return false;
    }
}

