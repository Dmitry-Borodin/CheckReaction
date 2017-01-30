package com.two_two.checkreaction.models.firebase

import android.support.annotation.Keep
import java.io.Serializable

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
@Keep
data class FirebaseScienceResult(
        var currectHits: Int = 0, //of 10 attapts
        var average: Long = 0L,
        var username: String = ""
) : Serializable {
    //dtimestamp will fill up only in firebase cloud.
    var timestamp: Long? = 0

    companion object {
        val TAG = "FirebaseScienceResult"
        val HITS = "currectHits"
        val TIMESTAMP = "timestamp"
    }


    override fun equals(other: Any?): Boolean {
        if (other !is FirebaseScienceResult) {
            return false
        }
        if (other.currectHits != currectHits) return false
        if (other.average != average) return false
        return other.username == username
    }
}