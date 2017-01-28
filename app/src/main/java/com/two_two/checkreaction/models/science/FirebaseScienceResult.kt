package com.two_two.checkreaction.models.science

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
data class FirebaseScienceResult(
        val currectHits : Int, //of 10 attapts
        val average : Long
) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this.average)
        dest.writeInt(this.currectHits)
    }

    val CREATOR: Parcelable.Creator<FirebaseScienceResult> = object : Parcelable.Creator<FirebaseScienceResult> {
        override fun createFromParcel(source: Parcel): FirebaseScienceResult {
            return FirebaseScienceResult(average = source.readLong(), currectHits = source.readInt())
        }

        override fun newArray(size: Int): Array<FirebaseScienceResult?> {
            return arrayOfNulls(size)
        }
    }

}