package com.gs.coursera_algo.week_1;

import java.util.Comparator;

public final class SortedSetInversionCounterTest extends InversionCounterTest {
    @Override
    protected InversionCounter<Integer> createInstance(final Comparator<Integer> comparator) {
        return SortedSetInversionCounter.with(comparator);
    }
}
