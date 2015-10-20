package inversions;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import inversions.Inversions.Result;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class TestInversions {

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
    public void verify_merge_merges_empty_arrays() {
        // Given
        final ArrayList<Integer> xs = new ArrayList<>();
        final ArrayList<Integer> ys = new ArrayList<>();

        final Result<Integer> rxs = Result.of(0L, xs);
        final Result<Integer> rys = Result.of(0L, ys);

        final Comparator<Integer> comparator = Comparator.<Integer>naturalOrder();

        // When
        final Result<Integer> result = Inversions.merge(rxs, rys, comparator);

        // Then
        assertThat(result.inversionNum(), equalTo(0L));
        assertThat(result.sortedArray(), empty());
    }

    private <T> long bruteForceCountInversions(final List<T> xs, final Comparator<T> comparator) {
        // TODO: Implement
        final int n = xs.size();

        int inversionNum = 0;

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

    @Test
    public void verify_countInversions_matches_brute_force() {
        // Given
        final List<Integer> xs = ImmutableList.of(5, 4, 2, 1, 1, 2, 5, 5, 10, 43, 12);
        final Comparator<Integer> comparator = Comparator.naturalOrder();

        // When
        final long resultBrute = bruteForceCountInversions(xs, comparator);
        final long result = Inversions.countInversions(xs, comparator);

        //Then
        assertThat(result, equalTo(resultBrute));
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
