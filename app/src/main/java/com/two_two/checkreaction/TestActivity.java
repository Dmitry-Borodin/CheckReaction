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
    boolean colourChenged = false;
    boolean complexTestFinished = false;
    long startPoint; //this just for difference used in simpleTest
    int iteration=1; //used only in complexTest
    ArrayList<Long> complecxReactionList = new ArrayList<>(6);

    private void makeSimpleTest() {
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.BLUE);
        colourChenged = true;
        startPoint = System.currentTimeMillis();
        Log.d(ReactionTest.TAG, "testMethod finishing");
    }

    private void makeComplexTest(){
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
//        String colourName="R.color.test"+iteration;
//        Color backgroundColor = getResources().getColor(R.color.test1);
        switch (iteration){
            case 1:l1.setBackgroundColor(getResources().getColor(R.color.test1)); //TODO initialise colour before
                break;
            case 2:l1.setBackgroundColor(getResources().getColor(R.color.test2));
                break;
            case 3:l1.setBackgroundColor(getResources().getColor(R.color.test3));
                break;
            case 4:l1.setBackgroundColor(getResources().getColor(R.color.test4));
                break;
            case 5:l1.setBackgroundColor(getResources().getColor(R.color.test5));
                break;
            case 6:l1.setBackgroundColor(getResources().getColor(R.color.test6));
                break;
            default:l1.setBackgroundColor(Color.WHITE);
                break;
        }
        iteration++;
        colourChenged=true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.GREEN);
        l1.setOnTouchListener(this);
        switch (ReactionTest.currentTestType){
            case simpleTest:{
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        makeSimpleTest();
                    }
                }, getRandomTime());
            }
            case complexTryTest:{

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        makeComplexTest();
                    }
                }, getRandomTime());
                complecxReactionList.add(System.currentTimeMillis());
            }
        }

//        Log.d(ReactionTest.TAG, "result in onStart" + result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (colourChenged) { //if not pushed before colour chenged
            switch (ReactionTest.currentTestType){
                case simpleTest:
                    result = System.currentTimeMillis() - startPoint;
                    gotoFinishActivity();
                    break;
                case complexTryTest:{
                    if (!complexTestFinished){
                        complecxReactionList.add(System.currentTimeMillis());
                        new Handler().postDelayed(new Runnable() {
                            public void run() {  //make one more test
                                makeComplexTest();
                            }
                        }, getRandomTime());
                        colourChenged=false;
                        if (complecxReactionList.size()>=ReactionTest.COMPLEXTRYTESTCOUNTER) complexTestFinished=true;
                        break;
                    }else {
                        complecxReactionList.add(System.currentTimeMillis());
                        if (complecxReactionList.size() < 2) break;
                        result = 0;
                        for (int i = 1; i < complecxReactionList.size(); i++) {
                            result += complecxReactionList.get(i) - complecxReactionList.get(i - 1);
                            Log.e(ReactionTest.TAG, "result now ="+result+" geti="+complecxReactionList.get(i)+ " geti-1="+complecxReactionList.get(i - 1));
                        }
                        result = result / (complecxReactionList.size() - 1);
                        gotoFinishActivity();
                        break;
                    }
                }
                default:
                    Log.e(ReactionTest.TAG, "error in onTouch switch 1 - default working");
                    throw new Error();
            }

        }else {
            result=0;
            gotoFinishActivity();
        }
        return false;
    }

    private int getRandomTime(){
        return random.nextInt(5000) + random.nextInt(1000) + 500;
    }
    private void gotoFinishActivity(){
        //            Log.d(ReactionTest.TAG, "Finish result " + result);
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
        finish();
    }
}

//TODO two buttons with different tests
//TODO local database
//TODO internet database