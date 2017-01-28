package com.two_two.checkreaction.ui.startscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.two_two.checkreaction.R;
import com.two_two.checkreaction.domain.App;
import com.two_two.checkreaction.domain.game.TestType;
import com.two_two.checkreaction.ui.reactiontest.TestActivity;


public class StartActivity extends Activity implements StartActivityContract {

    private StartScreenPresenter mPresenter;
    private EditText mUsernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mUsernameText = (EditText) findViewById(R.id.ac_start_username_edittext);
        mUsernameText.setText(App.getInstance().getLocalData().getUsername());
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
    public void onClickSimpleTest(View view) {
        saveUsername();
        mPresenter.startSimpleTest();
    }

    public void onClickComplexTest(View view) {
        saveUsername();
        mPresenter.startComplexTest();
    }

    private void saveUsername() {
        mPresenter.saveUsername(mUsernameText.getText().toString().trim());
    }
}