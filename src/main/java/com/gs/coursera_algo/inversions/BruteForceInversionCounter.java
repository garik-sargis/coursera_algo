package com.gs.coursera_algo.inversions;

import java.util.Comparator;
import java.util.List;

public class BruteForceInversionCounter<T> implements InversionCounter<T> {

    public static <S> BruteForceInversionCounter<S> with(Comparator<S> comparator) {
        return new BruteForceInversionCounter<>(comparator);
    }

    public static <S extends Comparable<S>> BruteForceInversionCounter<S> withNaturalOrder() {
        return new BruteForceInversionCounter<>(Comparator.<S>naturalOrder());
    }

    private final Comparator<T> mComparator;

    private BruteForceInversionCounter(final Comparator<T> comparator) {
        mComparator = comparator;
    }

    @Override
    public long countInversions(final List<T> xs) {
        // Length of the list
        final int n = xs.size();

        // The number of encountered inversions
        long inversionNum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // if (xs.get(j) < xs.get(i))
                if (mComparator.compare(xs.get(j), xs.get(i)) < 0) {
                    inversionNum++;
                }
            }
        }

        return inversionNum;
    }
}
