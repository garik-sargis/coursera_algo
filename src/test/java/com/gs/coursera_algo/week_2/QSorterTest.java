package com.gs.coursera_algo.week_2;

import com.google.common.collect.ImmutableList;
import com.gs.coursera_algo.week_2.QSorter.PivotPicker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.gs.coursera_algo.week_2.util.Matchers.sortedWith;
import static com.gs.coursera_algo.week_2.PivotPickers.rightmost;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public final class QSorterTest {

    private static Object[] param(final PivotPicker<Integer> picker,
                                  final List<Integer> xs,
                                  final long expectedComparisonNum) {
        return new Object[]{picker, xs, expectedComparisonNum};
    }

    @Parameterized.Parameters
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public static List<Object[]> data() {
        final ImmutableList.Builder<Object[]> builder = ImmutableList.builder();

        // input | expectedOutput

        builder.add(param(PivotPickers.leftmost(), new ArrayList<>(), 0));
        builder.add(param(rightmost(), asList(1), 0));
        builder.add(param(rightmost(), asList(1, 2), 1));
        builder.add(param(rightmost(), asList(2, 1), 1));
        builder.add(param(rightmost(), asList(1, 2, 3), 3));
        builder.add(param(rightmost(), asList(3, 2, 1), 3));
        builder.add(param(rightmost(), asList(1, 3, 2), 2));

        return builder.build();
    }

    private final List<Integer> mXs;
    private final long mExpextedComparisonNum;

    private final Comparator<Integer> mComparator;
    private final QSorter<Integer> mQSorter;

    public QSorterTest(final PivotPicker<Integer> pivotPicker,
                       final List<Integer> xs,
                       final long expextedComparisonNum) {
        mXs = xs;
        mExpextedComparisonNum = expextedComparisonNum;

        mComparator = Comparator.naturalOrder();
        mQSorter = QSorter.with(mComparator, pivotPicker);
    }


    @Test
    public void test() {
        // Given parameters

        // When
        final long comparisonNum = mQSorter.sort(mXs);

        // Then
        assertThat(mXs, sortedWith(mComparator));
        assertThat(comparisonNum, equalTo(mExpextedComparisonNum));
    }
}