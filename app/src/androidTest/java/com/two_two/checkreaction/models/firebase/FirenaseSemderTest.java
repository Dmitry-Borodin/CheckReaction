package com.two_two.checkreaction.models.firebase;


import org.junit.Test;

public class FirenaseSemderTest {

    @Test(expt)
    public void send_standsrt_result_to_firebase() {
        FirebaseComplexResult result = new FirebaseComplexResult(1000,1000,"test");
        FirebaseSender.getInstance().updateResult(result);
    }
}