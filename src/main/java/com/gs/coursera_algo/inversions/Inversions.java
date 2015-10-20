package com.gs.coursera_algo.inversions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


// TODO: Use instances
public final class Inversions {
    public static <T> long countInversions(final List<T> xs, final Comparator<T> comparator) {
        // Preconditions
        checkNotNull(xs);
        checkNotNull(comparator);

        return countInversionsAndSort(Collections.unmodifiableList(xs), comparator).inversionNum();
    }

    public static <T extends Comparable<T>> long countInversions(final List<T> xs) {
        return countInversions(xs, Comparator.naturalOrder());
    }


    private static <T> Result<T> countInversionsAndSort(final List<T> xs, final Comparator<T> comparator) {
        final int n = xs.size();

        // Base case: if n is 0 or 1
        if (n < 2) {
            return Result.of(0L, xs);
        } else /*if (n >= 2)*/ {
            // The left half
            final List<T> ys = xs.subList(0, n / 2);
            // The right half
            final List<T> zs = xs.subList(n / 2, n);

            // Recursive calls
            final Result<T> rys = countInversionsAndSort(ys, comparator);
            final Result<T> rzs = countInversionsAndSort(zs, comparator);

            // Combine (merge) the results of the recursive calls
            return merge(rys, rzs, comparator);
        }
    }

    static <T> Result<T> merge(final Result<T> rxs, final Result<T> rys, Comparator<T> comparator) {
        // Sorted arrays
        final List<T> xs = rxs.sortedArray();
        final List<T> ys = rys.sortedArray();
        // Array lengths
        final int m = xs.size();
        final int n = ys.size();

        int i = 0;
        int j = 0;
        long inversionNum = rxs.inversionNum() + rys.inversionNum();
        final ArrayList<T> zs = new ArrayList<>();

        while (i < m && j < n) {
            final T x = xs.get(i);
            final T y = ys.get(j);
            // if (x <= y)
            if (comparator.compare(x, y) <= 0) {
                zs.add(x);
                i++;
            } else /* if (x > y) */ {
                zs.add(y);
                inversionNum += (m - i);
                j++;
            }
        }

        // Append the remaining elements to the result
        zs.addAll(xs.subList(i, m));
        zs.addAll(ys.subList(j, n));

        return Result.of(inversionNum, zs);
    }

    static final class Result<T> {

        public static <T> Result<T> of(final long inversionNum, final List<T> sortedArray) {
            return new Result<>(inversionNum, sortedArray);
        }

        private final long mInversionNum;
        private final List<T> mSortedArray;

        private Result(final long inversionNum, final List<T> sortedArray) {
            mInversionNum = inversionNum;
            mSortedArray = sortedArray;
        }

        public long inversionNum() {
            return mInversionNum;
        }

        public List<T> sortedArray() {
            return mSortedArray;
        }
    }
}
