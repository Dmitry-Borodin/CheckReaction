package com.two_two.checkreaction.startscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.two_two.checkreaction.R;
import com.two_two.checkreaction.models.game.TestType;
import com.two_two.checkreaction.reactiontest.TestActivity;
import io.fabric.sdk.android.Fabric;


public class StartActivity extends Activity implements StartActivityContract {

    private StartScreenPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_start);
        mPresenter = StartScreenPresenter.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.registerActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unregisterActivity(this);
    }

    @Override
    public void startTest(TestType testType) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(TestType.TAG, testType);
        startActivity(intent);
    }

    //method defined in activity XML
    public void onClickSimpleTest(View view){
        mPresenter.startSimpleTest();
    }

    public void onClickComplexTest(View view){
        mPresenter.startComplexTest();
    }

}