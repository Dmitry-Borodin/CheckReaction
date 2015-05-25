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
import java.util.ArrayList;
import java.util.Random;


public class TestActivity extends Activity implements View.OnTouchListener {

    Random random = new Random();
    long result = 0; // my time reaction
    boolean testing = false;
    long startPoint; //this just for difference used in simpleTest
    int iteration; //used only in complexTest
    ArrayList<Long> complecxReactionList = new ArrayList<>(6);

    private void makeSimpleTest() {
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.BLUE);
        testing = true;
        startPoint = System.currentTimeMillis();
        Log.d(ReactionTest.TAG, "testMethod finishing");
    }

    private void makeComplexTest(){
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        //TODO make this and onstart
    }

    @Override
    protected void onStart() {
        super.onStart();
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.GREEN);
        l1.setOnTouchListener(this);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                makeSimpleTest();
            }
        }, random.nextInt(5000) + random.nextInt(1000) + 500);
//        Log.d(ReactionTest.TAG, "result in onStart" + result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (testing) {
            switch (ReactionTest.currentTestType){
                case simpleTest:
                    result = System.currentTimeMillis() - startPoint;
                    break;
                case complexTryTest:{
                    complecxReactionList.add(System.currentTimeMillis());
                    if (complecxReactionList.size()<2) break;
                    result = complecxReactionList.get(0);
                    for (int i=1; i<complecxReactionList.size();i++){
                        result +=complecxReactionList.get(i)-complecxReactionList.get(i-1);
                    }
                    result = result /(complecxReactionList.size()-1);
                    break;
                }
                default:
                    Log.e(ReactionTest.TAG, "error in onTouch switch 1 - default working");
                    throw new Error();
            }
//            Log.d(ReactionTest.TAG, "Finish result " + result);
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("result", result);
            startActivity(intent);
            finish();

        }else switch (ReactionTest.currentTestType){
            case simpleTest:
                break;
            case complexTryTest:
                complecxReactionList.add(System.currentTimeMillis());
                break;
            default:
                Log.e(ReactionTest.TAG,"error in onTouch switch 2 - default working");
                throw new Error();
        }
        return false;
    }
}

//TODO two buttons with different tests
//TODO local database
//TODO internet database