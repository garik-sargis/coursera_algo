package com.gs.coursera_algo.week_1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public final class MSortingInversionCounterVsBruteForceTest {

    /**
     * @return the number of inversions in the supplied list using a simple brute-force approach
     */
    private static <T> long bruteForceCountInversions(final List<T> xs, final Comparator<T> comparator) {
        // Length of the list
        final int n = xs.size();

        // Inversion counter
        long inversionNum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // if (xs.get(j) < xs.get(i))
                if (comparator.compare(xs.get(j), xs.get(i)) < 0) {
                    inversionNum++;
                }
            }
        }

        return inversionNum;
    }

    public static List<Integer> generateRandomList(final long seed, final int size) {
        return new Random(seed).ints(size)
                .boxed()
                .collect(Collectors.toList());
    }

    private final static long DATA_SIZE = 100L;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        final Comparator<Integer> naturalOrder = Comparator.naturalOrder();

        return LongStream.range(0, DATA_SIZE)
                .mapToObj(seed -> generateRandomList(seed, (int) seed))
                .map(list -> new Object[]{list, bruteForceCountInversions(list, naturalOrder)})
                .collect(Collectors.toList());
    }

    private final List<Integer> mInput;

    private final long mExpectedOutput;

    public MSortingInversionCounterVsBruteForceTest(final List<Integer> input, final long expectedOutput) {
        mInput = input;
        mExpectedOutput = expectedOutput;
    }

    @Test
    public void verify_countInversions_matches_brute_force() {
        // Given input
        final MSortingInversionCounter<Integer> inversionCounter = MSortingInversionCounter.withNaturalOrder();
        // When
        final long output = inversionCounter.countInversions(mInput);

        // Then
        assertThat(output, equalTo(mExpectedOutput));
    }
}
