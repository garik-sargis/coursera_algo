package com.gs.coursera_algo.week_2.util;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Comparator;
import java.util.List;

public class IsSortedList<E> extends TypeSafeMatcher<List<? extends E>> {

    private static <E> boolean isSorted(List<? extends E> xs, Comparator<E> comparator) {
        if (xs.isEmpty()) {
            return true;
        } else {
            E max = xs.get(0);
            for (final E x : xs) {
                if (comparator.compare(x, max) < 0) {
                    return false;
                } else {
                    max = x;
                }
            }

            return true;
        }
    }

    private final Comparator<E> mComparator;

    public IsSortedList(final Comparator<E> comparator) {
        mComparator = comparator;
    }

    @Override
    public boolean matchesSafely(List<? extends E> xs) {
        return isSorted(xs, mComparator);
    }

    @Override
    public void describeMismatchSafely(List<? extends E> item, Description mismatchDescription) {
        mismatchDescription.appendValue(item);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a sorted collection");
    }

    @Factory
    public static <E> Matcher<List<? extends E>> sortedWith(Comparator<E> comparator) {
        return new IsSortedList<>(comparator);
    }

    @Factory
    public static <E> Matcher<List<E>> sortedListOf(Class<E> type, Comparator<E> comparator) {
        @SuppressWarnings({"rawtypes", "unchecked"})
        final Matcher<List<E>> result = (Matcher) sortedWith(comparator);
        return result;
    }
}
