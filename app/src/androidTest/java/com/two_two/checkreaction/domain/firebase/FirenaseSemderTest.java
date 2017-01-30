package com.two_two.checkreaction.domain.firebase;


import com.two_two.checkreaction.models.firebase.FirebaseComplexResult;

import org.junit.Test;

public class FirenaseSemderTest {

    @Test
    public void send_standsrt_result_to_firebase() {
        FirebaseComplexResult result = new FirebaseComplexResult(1000,1000,"test");
        FirebaseSender.getInstance().updateComplexTestResult(result);
    }
}