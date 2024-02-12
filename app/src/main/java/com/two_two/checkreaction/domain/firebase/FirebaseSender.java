//package com.two_two.checkreaction.domain.firebase;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ServerValue;
//import com.two_two.checkreaction.BuildConfig;
//import com.two_two.checkreaction.models.firebase.FirebaseComplexResult;
//import com.two_two.checkreaction.models.firebase.FirebaseScienceResult;
//import com.two_two.checkreaction.utils.Constants;
//
//import org.jetbrains.annotations.NotNull;
//
//
//public class FirebaseSender {
//
//    private static volatile FirebaseSender instance;
//    private FirebaseComplexResult complexTestResult;
//    private FirebaseScienceResult scienceResult;
//
//    public static FirebaseSender getInstance() {
//        FirebaseSender instance = FirebaseSender.instance;
//        if (instance == null) {
//            synchronized (FirebaseSender.class) {
//                instance = FirebaseSender.instance;
//                if (instance == null) {
//                    FirebaseSender.instance = instance = new FirebaseSender();
//                }
//            }
//        }
//        return instance;
//    }
//
//    /**
//     * Adds new record score to remote database if needed.
//     */
//    public void updateComplexTestResult(@NotNull FirebaseComplexResult testResult) {
//        if (complexTestResult != null && complexTestResult.equals(testResult)) {
//            return;
//        }
//        complexTestResult = testResult;
//
//        DatabaseReference fb = FirebaseDatabase.getInstance().getReference(BuildConfig.FIREBASE_GAMESCORES);
//        fb = fb.push(); //new line in db
//        fb.setValue(testResult);
//        fb.child(Constants.TIMESTAMP).setValue(ServerValue.TIMESTAMP);
//    }
//
//    public void updateScienceTestResult(@NotNull FirebaseScienceResult testResult) {
//        if (scienceResult != null && scienceResult.equals(testResult)) {
//            return;
//        }
//        scienceResult = testResult;
//
//        DatabaseReference fb = FirebaseDatabase.getInstance().getReference()
//                .child(BuildConfig.SCIENCE_GAMESCORES);
//        fb = fb.push(); //new line in db
//        fb.setValue(testResult);
//        fb.child(Constants.TIMESTAMP).setValue(ServerValue.TIMESTAMP);
//    }
//}
