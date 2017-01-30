package com.two_two.checkreaction.ui.gamescore.science

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.google.firebase.database.Query
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.firebase.FirebaseScienceResult
import com.two_two.checkreaction.ui.gamescore.OnLoadListener

/**
 * @author Dmitry Borodin on 2017-01-30.
 */
class ScienceScoreListAdapter(
        val activity: Activity,
        ref: Query,
        val currentResult: FirebaseScienceResult?,
        val onLoadListener: OnLoadListener
) : FirebaseListAdapter<FirebaseScienceResult>(
        activity, FirebaseScienceResult::class.java, R.layout.item_score_result, ref) {

    private var mWasLoaded = false

    override fun populateView(v: View, model: FirebaseScienceResult, position: Int) {

        val number = v.findViewById(R.id.item_score_number) as TextView
        val username = v.findViewById(R.id.item_score_username) as TextView
        val average = v.findViewById(R.id.item_score_average) as TextView
        val median = v.findViewById(R.id.item_score_median) as TextView

        if (model.equals(currentResult)) {
            v.setBackgroundColor(Color.RED)
        }else {
            v.setBackgroundColor(Color.WHITE)
        }
        if (!mWasLoaded) {
            mWasLoaded = true
            onLoadListener.onLoaded()
        }

        val oneStartedPosition = position + 1 //positions from 1, not from 0
        number.text = "" + oneStartedPosition
        username.text = model.username
        average.text = model.average.toString()
        median.text = model.currectHits.toString()
    }
}