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
import android.widget.TextView;
import java.util.ArrayList;

public class TestActivity extends Activity implements View.OnTouchListener {

    long result = 0; // my time reaction
    boolean colourChenged = false;
    boolean complexTestFinished = false;
    long startPoint; //this just for difference used in simpleTest
    int iteration=1; //used only in complexTest
    ArrayList<Long> complecxReactionList = new ArrayList<>(6);
    TextView textDownSmall,textCenterCommandToStart;

    private void makeSimpleTest() {
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        textCenterCommandToStart.setText(getString(R.string.testing_command_tap));
        l1.setBackgroundColor(Color.BLUE);
        colourChenged = true;
        startPoint = System.currentTimeMillis();
//        Log.d(ReactionTest.TAG, "testMethod finishing");
    }

    private void makeComplexTest(){
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        startPoint = System.currentTimeMillis();
//        String colourName="R.color.test"+iteration; //this doen't work
//        Color backgroundColor = getResources().getColor(R.color.test1);
        textCenterCommandToStart.setText(getString(R.string.testing_command_tap));
        switch (iteration){
            case 1:l1.setBackgroundColor(getResources().getColor(R.color.test1));
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
            default:l1.setBackgroundColor(Color.BLACK);
                break;
        }
        iteration++;
        textDownSmall.setText("Iteration "+iteration+" from "+ReactionTest.COMPLEXTRYTESTCOUNTER);
        colourChenged=true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FrameLayout l1 = (FrameLayout) findViewById(R.id.TestLayout);
        l1.setBackgroundColor(Color.GREEN);
        l1.setOnTouchListener(this);
        switch (ReactionTest.currentTestType){
            case simpleTest:
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        makeSimpleTest();
                    }
                }, ReactionTest.getRandomTime());
                break;
            case complexTryTest:
                textDownSmall.setText("Iteration "+iteration+" from "+ReactionTest.COMPLEXTRYTESTCOUNTER);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        makeComplexTest();
                    }
                }, ReactionTest.getRandomTime());
                break;
            default:
                Log.e(ReactionTest.TAG, "error in onStart switch - default working");
                throw new Error();
        }
//        Log.d(ReactionTest.TAG, "result in onStart" + result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textDownSmall=(TextView)findViewById(R.id.textDownSmall);
        textDownSmall.setText("");
        textCenterCommandToStart=(TextView)findViewById(R.id.textCenterCommandToStart);
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
                        complecxReactionList.add(System.currentTimeMillis()-startPoint);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {  //make one more test
                                makeComplexTest();
                            }
                        }, ReactionTest.getRandomTime());
                        colourChenged=false;
                        textCenterCommandToStart.setText(getString(R.string.testing_string_ready));
                        if (complecxReactionList.size()>=ReactionTest.COMPLEXTRYTESTCOUNTER) complexTestFinished=true;
                        break;
                    }else {
                        complecxReactionList.add(System.currentTimeMillis()-startPoint);
                        if (complecxReactionList.size() < 2){
                            result = 1;
                            gotoFinishActivity();
                        }

                        for (int i = 0; i < complecxReactionList.size(); i++) {
                            result += complecxReactionList.get(i);
                            Log.e(ReactionTest.TAG, "result now ="+result+" geti="+complecxReactionList.get(i));
                        }
                        result = result / (complecxReactionList.size());
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

    private void gotoFinishActivity(){
        //            Log.d(ReactionTest.TAG, "Finish result " + result);
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
        finish();
    }
}

//TODO change style to same in all activities
//TODO make this work when rotate
//TODO local database
//TODO internet database