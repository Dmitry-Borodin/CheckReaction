package com.two_two.checkreaction.ui.startscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.two_two.checkreaction.R;
import com.two_two.checkreaction.domain.App;
import com.two_two.checkreaction.domain.game.TestType;
import com.two_two.checkreaction.ui.reactiontest.TestActivity;
import com.two_two.checkreaction.ui.scienceproject.ScienceActivitySplash;


public class StartActivity extends Activity implements StartActivityContract {

    private StartScreenPresenter mPresenter;
    private EditText mUsernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mUsernameText = (EditText) findViewById(R.id.ac_start_username_edittext);
        final String lastUsedUsername = App.getInstance().getLocalData().getUsername();
        mUsernameText.setText(lastUsedUsername);
        mPresenter = new StartScreenPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.boundActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unboundActivity();
    }

    @Override
    public void startTest(TestType testType) {
        switch (testType) {
            case SCIENCE_TEST:
                Intent intent = new Intent(this, ScienceActivitySplash.class);
                startActivity(intent);
                break;
            case COMPLEX_TEST:
            case SIMPLE_TEST:
                intent = new Intent(this, TestActivity.class);
                intent.putExtra(TestType.TAG, testType);
                startActivity(intent);
                break;
            default:
                throw new UnsupportedOperationException("this test type not implemented yet");
        }
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

    public void onClickScienceTest(View view) {
        saveUsername();
        mPresenter.startScienceTest();
    }
}