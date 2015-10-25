package com.gs.coursera_algo.week_2;

import com.google.common.collect.ImmutableList;
import com.gs.coursera_algo.week_2.QSorter.PivotPicker;
import org.hamcrest.*;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

// TODO: Refactor
public class PivotPickersTest {

    @Test
    public void test_median1() {
        // Given
        final PivotPicker<Integer> picker = PivotPickers.median(Comparator.<Integer>naturalOrder());
        final ImmutableList<Integer> xs = ImmutableList.of(1, 4, 2, 5, 3);

        // When
        final int pivotIndex = picker.pick(xs);

        // Then
        assertThat(pivotIndex, equalTo(2));
    }

    @Test
    public void test_median2() {
        // Given
        final PivotPicker<Integer> picker = PivotPickers.median(Comparator.<Integer>naturalOrder());
        final ImmutableList<Integer> xs = ImmutableList.of(3, 2, 4, 1);

        // When
        final int pivotIndex = picker.pick(xs);

        // Then
        assertThat(pivotIndex, equalTo(1));
    }

    @Test
    public void test_median3() {
        // Given
        final PivotPicker<Integer> picker = PivotPickers.median(Comparator.<Integer>naturalOrder());
        final ImmutableList<Integer> xs = ImmutableList.of(1, 3, 2);

        // When
        final int pivotIndex = picker.pick(xs);

        // Then
        assertThat(pivotIndex, equalTo(2));
    }
}