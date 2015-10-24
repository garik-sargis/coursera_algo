package com.gs.coursera_algo.inversions;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

// TODO: Investigate assumptions
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class InversionCounterTest {

    @Rule
    public final Timeout mTimeoutRule = new Timeout(2000);

    protected abstract InversionCounter<Integer> createInstance(Comparator<Integer> comparator);

    private InversionCounter<Integer> mInversionCounter;

    @Before
    public final void initializeInstance() {
        mInversionCounter = createInstance(Comparator.<Integer>naturalOrder());
    }

    @Test(expected = NullPointerException.class)
    public final void verify_countInversions_throws_NullPointerException_if_receives_null_array() {
        // Given
        final List<Integer> xs = null;

        // When
        mInversionCounter.countInversions(xs);

        // Then
        // Throws NullPointerException
    }

    @Test
    public void verify_no_inversions_in_sorted_lists() {
        // Given
        final int min = 0;
        final int max = 100000;
        final List<Integer> xs = IntStream.range(min, max + 1)
                .boxed().collect(Collectors.toList());

        // When
        final long inversions = mInversionCounter.countInversions(xs);

        // Then
        assertThat(inversions, equalTo(0L));
    }

    @Test
    public void verify_inversions_in_reverse_sorted_list() {
        // Given
        final int max = 100000;
        final int min = 0;
        final int n = max - min + 1;
        final List<Integer> xs = IntStream.iterate(max, x -> x - 1)
                .limit(n)
                .boxed().collect(Collectors.toList());

        // When
        final long inversions = mInversionCounter.countInversions(xs);

        // Then
        final long expectedResult = ((long) n * ((long) n - 1)) / 2;
        assertThat(inversions, equalTo(expectedResult));
    }
}
