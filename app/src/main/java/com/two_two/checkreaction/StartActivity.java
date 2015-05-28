package com.two_two.checkreaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class StartActivity extends Activity {
 //   View startView = findViewById(r.id.StActivity);

/*    public void startCheckMethod(View view){ //not used anymore. Was used from layout
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }*/

    public void startSimpleMethod(View view){
        ReactionTest.currentTestType=ReactionTest.testType.simpleTest;
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void startComplexMethod(View view){
        ReactionTest.currentTestType=ReactionTest.testType.complexTryTest;
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(ReactionTest.TAG,"start created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

}