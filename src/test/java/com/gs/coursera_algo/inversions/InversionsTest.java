package com.gs.coursera_algo.inversions;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import com.gs.coursera_algo.inversions.Inversions.Result;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

// TODO: Investigate assumptions
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InversionsTest {

    @Rule
    public final Timeout mTimeoutRule = new Timeout(2000);

    @Test(expected = NullPointerException.class)
    public void verify_countInversions_throws_NullPointerException_if_receives_null_array() {
        final Comparator mockComparator = Mockito.mock(Comparator.class);
        Inversions.countInversions(null, mockComparator);
    }

    @Test(expected = NullPointerException.class)
    public void verify_countInversions_throws_NullPointerException_if_receives_null_comparator() {
        final ArrayList mockList = Mockito.mock(ArrayList.class);
        Inversions.countInversions(mockList, null);
    }

    @Test
    public void verify_merging_empty_arrays_results_in_an_empty_array() {
        // Given

        // Empty lists
        final List<Integer> xs = ImmutableList.of();
        final List<Integer> ys = ImmutableList.of();

        // Empty lists with 0 inversions
        final Result<Integer> rxs = Result.of(0L, xs);
        final Result<Integer> rys = Result.of(0L, ys);

        final Comparator<Integer> comparator = Comparator.naturalOrder();

        // When
        final Result<Integer> result = Inversions.merge(rxs, rys, comparator);

        // Then
        assertThat(result.inversionNum(), equalTo(0L));
        assertThat(result.sortedList(), empty());
    }

    @Test
    public void verify_no_inversions_in_sorted_lists() {
        // Given
        final ImmutableList<Integer> xs = ContiguousSet.create(Range.closedOpen(0, 100000), DiscreteDomain.integers())
                .asList();
        final Comparator<Integer> comparator = Comparator.naturalOrder();

        // When
        final long inversions = Inversions.countInversions(xs, comparator);

        // Then
        assertThat(inversions, equalTo(0L));
    }
}
