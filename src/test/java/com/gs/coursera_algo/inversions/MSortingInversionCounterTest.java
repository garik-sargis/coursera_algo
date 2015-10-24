package com.gs.coursera_algo.inversions;

import java.util.Comparator;

public class MSortingInversionCounterTest extends InversionCounterTest {
    @Override
    protected InversionCounter<Integer> createInstance(final Comparator<Integer> comparator) {
        return MSortingInversionCounter.with(comparator);
    }
}
