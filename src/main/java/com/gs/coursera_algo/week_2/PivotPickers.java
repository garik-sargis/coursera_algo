package com.gs.coursera_algo.week_2;

import java.util.Comparator;

public final class PivotPickers {

    private PivotPickers() {
        // Do not instantiate
    }

    public static <E> QSorter.PivotPicker<E> leftmost() {
        return xs -> 0;
    }

    public static <E> QSorter.PivotPicker<E> rightmost() {
        return xs -> xs.size() - 1;
    }

    public static <E> QSorter.PivotPicker<E> median(Comparator<E> comparator) {
        return xs -> {
            final int n = xs.size();
            final int l = 0;
            final int m = (n - 1) / 2;
            final int r = n - 1;

            final E xl = xs.get(l);
            final E xm = xs.get(m);
            final E xr = xs.get(r);

            int pivotIndex;

            if (comparator.compare(xl, xr) < 0) {
                if (comparator.compare(xm, xl) < 0) {
                    pivotIndex = l;
                } else if (comparator.compare(xr, xm) < 0) {
                    pivotIndex = r;
                } else {
                    pivotIndex = m;
                }
            } else {
                if (comparator.compare(xl, xm) < 0) {
                    pivotIndex = l;
                } else if (comparator.compare(xr, xm) < 0) {
                    pivotIndex = m;
                } else {
                    pivotIndex = r;
                }
            }

            return pivotIndex;
        };
    }
}
