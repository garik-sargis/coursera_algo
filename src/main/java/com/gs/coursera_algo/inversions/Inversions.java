package com.gs.coursera_algo.inversions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


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

            // Recursive call results
            final Result<T> rys = countInversionsAndSort(ys, comparator);
            final Result<T> rzs = countInversionsAndSort(zs, comparator);

            // Combine (merge) the results of the recursive calls
            return merge(rys, rzs, comparator);
        }
    }

    static <T> Result<T> merge(final Result<T> rx, final Result<T> ry, Comparator<T> comparator) {
        // Sorted lists
        final List<T> xs = rx.sortedList();
        final List<T> ys = ry.sortedList();
        // List lengths
        final int nx = xs.size();
        final int ny = ys.size();

        int i = 0;
        int j = 0;
        long inversionNum = rx.inversionNum() + ry.inversionNum();
        final ArrayList<T> zs = new ArrayList<>();

        while (i < nx && j < ny) {
            final T x = xs.get(i);
            final T y = ys.get(j);
            // if (x <= y)
            if (comparator.compare(x, y) <= 0) {
                zs.add(x);
                i++;
            } else /* if (x > y) */ {
                zs.add(y);
                inversionNum += (nx - i);
                j++;
            }
        }

        // Append the remaining elements to the result
        zs.addAll(xs.subList(i, nx));
        zs.addAll(ys.subList(j, ny));

        return Result.of(inversionNum, zs);
    }

    static final class Result<T> {

        public static <T> Result<T> of(final long inversionNum, final List<T> sortedList) {
            return new Result<>(inversionNum, sortedList);
        }

        private final long mInversionNum;
        private final List<T> mSortedList;

        private Result(final long inversionNum, final List<T> sortedList) {
            mInversionNum = inversionNum;
            mSortedList = sortedList;
        }

        public long inversionNum() {
            return mInversionNum;
        }

        public List<T> sortedList() {
            return mSortedList;
        }
    }
}
