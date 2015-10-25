package com.gs.coursera_algo.week_2;

import java.util.Comparator;
import java.util.List;

public final class QSorter<E> {

    public interface PivotPicker<E> {
        int pick(List<E> xs);
    }

    public static <E> QSorter<E> with(final Comparator<E> comparator, final PivotPicker<E>
            pivotPicker) {
        return new QSorter<>(comparator, pivotPicker);
    }

    private final Comparator<E> mComparator;
    private final PivotPicker<E> mPivotPicker;

    private QSorter(final Comparator<E> comparator, final PivotPicker<E> pivotPicker) {
        // Empty
        mComparator = comparator;
        mPivotPicker = pivotPicker;
    }

    // | a0 | a1 | a2 |...| ak |...| an |
    // 0    1    2    3   k   k+1  n   n+1

    private void swap(final List<E> xs, final int i, final int j) {
        final E xi = xs.get(i);
        final E xj = xs.get(j);
        xs.set(i, xj);
        xs.set(j, xi);
    }

    private Partition<E> partition(final List<E> xs, final int pivotIndex) {
        // List size
        final int n = xs.size();

        // Place the pivot in the leftmost position
        swap(xs, 0, pivotIndex);

        // Pivot
        final E p = xs.get(0);

        int i = 1;
        for (int j = 1; j < n; j++) {
            // if (xs[j] < p)
            if (mComparator.compare(xs.get(j), p) < 0) {
                swap(xs, j, i);
                i++;
            }
        }

        final int finalPivotIndex = i - 1;
        swap(xs, 0, finalPivotIndex);

        final List<E> left = xs.subList(0, finalPivotIndex);
        final List<E> right = xs.subList(finalPivotIndex + 1, n);
        return Partition.of(left, right);
    }

    /**
     * Sorts the supplied list in an order determined by the supplied comparator.
     *
     * @return the number of element comparisons made.
     */
    public long sort(final List<E> xs) {
        long comparisonNum = 0;

        if (xs.size() >= 2) {
            comparisonNum = xs.size() - 1;

            final Integer pivotIndex = mPivotPicker.pick(xs);
            final Partition<E> partition = partition(xs, pivotIndex);

            final long cl = sort(partition.left());
            final long cr = sort(partition.right());

            comparisonNum += (cl + cr);
        } /* else {
            Already sorted
        }*/

        return comparisonNum;
    }

    private static class Partition<E> {
        public static <E> Partition<E> of(final List<E> left, final List<E> right) {
            return new Partition<>(left, right);
        }

        private final List<E> mLeft;
        private final List<E> mRight;

        private Partition(final List<E> left, final List<E> right) {
            mLeft = left;
            mRight = right;
        }

        public List<E> left() {
            return mLeft;
        }

        public List<E> right() {
            return mRight;
        }
    }
}
