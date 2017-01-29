package com.two_two.checkreaction.models.science

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
data class FirebaseScienceResult(
        val currectHits: Int, //of 10 attapts
        val average: Long,
        val username: String
) : Serializable {
    //dtimestamp will fill up only in firebase cloud.
    var timestamp: Long? = 0

    companion object {
        val TAG = "FirebaseScienceResult"
        val HITS = "currectHits"
    }
}