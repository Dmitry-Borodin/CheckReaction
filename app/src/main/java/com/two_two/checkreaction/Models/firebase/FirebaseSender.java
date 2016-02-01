package com.two_two.checkreaction.models.firebase;

import com.firebase.client.Firebase;
import com.two_two.checkreaction.R;

/**
 * Adds new record score to remote database if needed.
 */
public class FirebaseSender {

    private static volatile FirebaseSender sInstance;
    private FirebaseComplexTestResult mComplexTestResult;

    public static FirebaseSender getInstance() {
        FirebaseSender instance = sInstance;
        if (instance == null) {
            synchronized (FirebaseSender.class) {
                instance = sInstance;
                if (instance == null) {
                    sInstance = instance = new FirebaseSender();
                }
            }
        }
        return instance;
    }

    public void updateResult(FirebaseComplexTestResult testResult) {
        if (mComplexTestResult.equals(testResult)) return;
        else mComplexTestResult = testResult;

        Firebase fb = new Firebase(R.string.firebase_root_url).child(R.string.firebase_gamescores);
        fb = fb.push(); //new line in db
        fb.setValue(testResult);

    }
}
