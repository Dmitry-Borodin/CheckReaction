package com.two_two.checkreaction.ui.gamescore.complex

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ListView

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.two_two.checkreaction.BuildConfig
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.firebase.FirebaseComplexResult
import com.two_two.checkreaction.ui.gamescore.OnLoadListener

/**
 * Showing gamescores for Complex Test from Firebase backend.
 *
 * Created by Dmitry Borodin on 2/1/2016.
 */
class ComplexScoreActivity : Activity() {

    private lateinit var mList: ListView
    private lateinit var mAdapter: ComplexScoresListAdapter
    private lateinit var firebase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_scores)
        init()
    }

    private fun init() {
        val dialog = ProgressDialog.show(this, getString(R.string.loading_title),
                getString(R.string.loading_message))
        mList = findViewById(R.id.ac_scores_list) as ListView
        val currentResult = intent.getParcelableExtra<FirebaseComplexResult>(FirebaseComplexResult.TAG)
        firebase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference= firebase.getReference(BuildConfig.FIREBASE_GAMESCORES)
        mAdapter = ComplexScoresListAdapter(this, myRef.orderByChild(FirebaseComplexResult.MEDIAN),
                currentResult, OnLoadListener { dialog.dismiss() })
        mList.adapter = mAdapter
    }
}
