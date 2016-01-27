package com.two_two.checkreaction.models.game;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Dmitry Borodin on 1/3/2016.
 */
public final class TestResult implements Parcelable {

    public static final String TAG = "TestResult";
    private final long mAverage;
    private final long mMedian;
    private final boolean mIsFailed;
    private final TestType mTestType;

    public TestResult(long averageReaction, long medianReaction, boolean isFailed, TestType testType) {
        this.mAverage = averageReaction;
        this.mMedian = medianReaction;
        this.mIsFailed = isFailed;
        this.mTestType = testType;
    }

    public long getAverage() {
        return mAverage;
    }

    public long getMedian() {
        return mMedian;
    }

    public boolean isFailed() {
        return mIsFailed;
    }

    public TestType getTestType() {
        return mTestType;
    }

    //parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mAverage);
        dest.writeLong(this.mMedian);
        dest.writeByte(mIsFailed ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mTestType == null ? -1 : this.mTestType.ordinal());
    }

    protected TestResult(Parcel in) {
        this.mAverage = in.readLong();
        this.mMedian = in.readLong();
        this.mIsFailed = in.readByte() != 0;
        int tmpMTestType = in.readInt();
        this.mTestType = tmpMTestType == -1 ? null : TestType.values()[tmpMTestType];
    }

    public static final Creator<TestResult> CREATOR = new Creator<TestResult>() {
        public TestResult createFromParcel(Parcel source) {
            return new TestResult(source);
        }

        public TestResult[] newArray(int size) {
            return new TestResult[size];
        }
    };
}
