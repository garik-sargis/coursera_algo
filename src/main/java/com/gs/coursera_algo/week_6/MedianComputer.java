package com.gs.coursera_algo.week_6;

import java.util.Comparator;
import java.util.PriorityQueue;

// [0] - -
// * * 0 * *
// * 0 * *
public class MedianComputer {

    public static MedianComputer create() {
        return new MedianComputer();
    }

    private int mSize;
    private final PriorityQueue<Integer> mLeftMaxHeap;
    private int mMedian;
    private final PriorityQueue<Integer> mRightMinHeap;

    private MedianComputer() {
        mSize = 0;
        mLeftMaxHeap = new PriorityQueue<>(Comparator.<Integer>reverseOrder());
        mRightMinHeap = new PriorityQueue<>();
    }

    public void add(final int val) {
        if (mSize == 0) {
            mMedian = val;
        } else {
            if(val <= mMedian) {
                mLeftMaxHeap.add(val);
                if(!isNormalized()) {
                    shiftRight();
                }
            } else {
                mRightMinHeap.add(val);
                if(!isNormalized()) {
                    shiftLeft();
                }
            }
        }
        mSize++;
    }

    private void shiftLeft() {
        final int rMin = mRightMinHeap.remove();
        final int min = Math.min(rMin, mMedian);
        final int max = Math.max(rMin, mMedian);
        mMedian = max;
        mLeftMaxHeap.add(min);
    }

    private void shiftRight() {
        final int lMax = mLeftMaxHeap.remove();
        final int min = Math.min(lMax, mMedian);
        final int max = Math.max(lMax, mMedian);
        mMedian = min;
        mRightMinHeap.add(max);
    }

    private boolean isNormalized() {
        final int dif = mRightMinHeap.size() - mLeftMaxHeap.size();
        return dif == 0 || dif == 1;
    }

    public int getMedian() {
        if (mSize == 0) {
            throw new IllegalStateException("Median of an empty list");
        } else {
            return mMedian;
        }
    }
}
