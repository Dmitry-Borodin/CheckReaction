package com.two_two.checkreaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FinishActivity extends ActionBarActivity {

    
    long Result;
    TextView ResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
//        Log.d(ReactionTest.TAG,"before getextre");
        Intent intent = getIntent();
        try {
        Result = intent.getLongExtra("result",1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(ReactionTest.TAG,"before findViewById");
        ResultView = (TextView)findViewById(R.id.ResultView);
        try {
            if (Result == 1) {
                ResultView.setText(getString(R.string.exception_like_Result_Is_1));
            } else if (Result == 0){
                ResultView.setText(getString(R.string.Too_fast));
            } else {
                switch (ReactionTest.currentTestType){
                    case simpleTest: ResultView.setText(getString(R.string.simple_reaction_is) + Result + getString(R.string.ms));
                        break;
                    case complexTryTest: ResultView.setText(getString(R.string.complex_reaction_is) + Result + getString(R.string.ms));
                        break;
                    default:
                        Log.e(ReactionTest.TAG,"error in onTouch switch - default working");
                        ResultView.setText(getString(R.string.ErrorInTestTypeSwitch));
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void againMethod(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
        finish();
//        if not finish here, why i getting old intent in onCreate?
    }
}

