package com.gs.coursera_algo.week_1;

import java.util.Comparator;

public final class MSortingInversionCounterTest extends InversionCounterTest {
    @Override
    protected InversionCounter<Integer> createInstance(final Comparator<Integer> comparator) {
        return MSortingInversionCounter.with(comparator);
    }
}
