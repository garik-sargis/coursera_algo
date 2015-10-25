package com.gs.coursera_algo.week_2.util;

import org.hamcrest.Matcher;

import java.util.Comparator;
import java.util.List;

public final class Matchers {
    private Matchers() {
    }

    public static <E> Matcher<List<? extends E>> sortedWith(final Comparator<E> comparator) {
        return new IsSortedList<>(comparator);
    }
}
