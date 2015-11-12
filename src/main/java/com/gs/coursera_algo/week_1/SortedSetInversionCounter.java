package com.gs.coursera_algo.week_1;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public final class SortedSetInversionCounter<E> implements InversionCounter<E> {

    public static <E> SortedSetInversionCounter<E> with(final Comparator<E> comparator) {
        return new SortedSetInversionCounter<>(comparator);
    }

    private final Comparator<E> mComparator;

    private SortedSetInversionCounter(final Comparator<E> comparator) {
        mComparator = comparator;
    }

    @Override
    public long countInversions(final List<E> xs) {
        final TreeSet<E> seenEntries = new TreeSet<>(mComparator);

        long inversionNum = 0;

        for (final E x : xs) {
            inversionNum += seenEntries.tailSet(x, false).size();
            seenEntries.add(x);
        }

        return inversionNum;
    }
}
