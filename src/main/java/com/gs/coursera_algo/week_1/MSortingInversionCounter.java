package com.gs.coursera_algo.week_1;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


// TODO: Don't test private methods
public final class MSortingInversionCounter<T> implements InversionCounter<T>{

    public static <S> MSortingInversionCounter<S> with(Comparator<S> comparator) {
        return new MSortingInversionCounter<>(comparator);
    }

    public static <S extends Comparable<S>> MSortingInversionCounter<S> withNaturalOrder() {
        return new MSortingInversionCounter<>(Comparator.<S>naturalOrder());
    }

    /**
     * A comparator (always not null).
     */
    private final Comparator<T> mComparator;

    private MSortingInversionCounter(final Comparator<T> comparator) {
        // Preconditions
        checkNotNull(comparator);
        mComparator = comparator;
    }

    public long countInversions(final List<T> xs) {
        // Preconditions
        checkNotNull(xs);

        return countInversionsAndSort(Collections.unmodifiableList(xs)).inversionNum();
    }


    private Result<T> countInversionsAndSort(final List<T> xs) {
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
            final Result<T> rys = countInversionsAndSort(ys);
            final Result<T> rzs = countInversionsAndSort(zs);

            // Combine (merge) the results of the recursive calls
            return merge(rys, rzs);
        }
    }

    private Result<T> merge(final Result<T> rx, final Result<T> ry) {
        // Sorted lists
        final List<T> xs = rx.sortedList();
        final List<T> ys = ry.sortedList();
        // List lengths
        final int nx = xs.size();
        final int ny = ys.size();

        int i = 0;
        int j = 0;
        long inversionNum = rx.inversionNum() + ry.inversionNum();
        final ImmutableList.Builder<T> builder = ImmutableList.builder();

        while (i < nx && j < ny) {
            final T x = xs.get(i);
            final T y = ys.get(j);
            // if (x <= y)
            if (mComparator.compare(x, y) <= 0) {
                builder.add(x);
                i++;
            } else /* if (x > y) */ {
                builder.add(y);
                inversionNum += (nx - i);
                j++;
            }
        }

        // Append the remaining elements to the result
        builder.addAll(xs.subList(i, nx));
        builder.addAll(ys.subList(j, ny));

        return Result.of(inversionNum, builder.build());
    }

    private static final class Result<T> {

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
