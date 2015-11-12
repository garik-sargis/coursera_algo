package com.gs.coursera_algo.week_4;

import com.google.common.collect.ImmutableList;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SccsTest {

    @Test
    public void testVeretexesByEndTimes() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .build();

        final List<Integer> result = Sccs.vertexesByFinishingTimes(graph);
        assertThat(result, equalTo(ImmutableList.of(2, 1)));
    }

    @Test
    public void testVeretexesByEndTimes2() {
        final Graph graph = Graph.builder()
                .addArc(2, 3)
                .addArc(1, 2)
                .build();

        final List<Integer> result = Sccs.vertexesByFinishingTimes(graph);
        assertThat(result, equalTo(ImmutableList.of(3, 2, 1)));
    }

    @Test
    public void testGetSccs() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .build();

        final List<List<Integer>> sccs = Sccs.getSccs(graph);

        assertThat(sccs.size(), equalTo(2));
    }
    @Test
    public void testGetSccs2() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .addArc(1, 3)
                .build();

        final List<List<Integer>> sccs = Sccs.getSccs(graph);

        assertThat(sccs.size(), equalTo(3));
    }

    @Test
    // One split
    public void testSccDescendingSizes() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .addArc(1, 3)
                .build();

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes, equalTo(ImmutableList.of(1, 1, 1)));
    }

    @Test
    // One loop of size 2
    public void testSccDescendingSizes2() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .addArc(2, 1)
                .build();

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes, equalTo(ImmutableList.of(2)));
    }

    @Test
    // Two disconnected loops of sizes 2 and 3
    public void testSccDescendingSizes3() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .addArc(2, 1)
                .addArc(3, 4)
                .addArc(4, 5)
                .addArc(5, 3)
                .build();

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes, equalTo(ImmutableList.of(3, 2)));
    }

    @Test
    // Two connected loops of sizes 2 and 3
    public void testSccDescendingSizes4() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .addArc(2, 1)
                .addArc(2, 3)
                .addArc(3, 4)
                .addArc(4, 5)
                .addArc(5, 3)
                .build();

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes, equalTo(ImmutableList.of(3, 2)));
    }

    @Test
    // One component of size 5
    public void testSccDescendingSizes5() {
        final Graph graph = Graph.builder()
                .addArc(1, 2)
                .addArc(2, 1)
                .addArc(2, 3)
                .addArc(3, 4)
                .addArc(4, 5)
                .addArc(5, 3)
                .addArc(5, 1)
                .build();

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes, equalTo(ImmutableList.of(5)));
    }

    private static Graph generateCycle(final int size) {
        final Graph.Builder builder = Graph.builder();
        for(int i = 1; i < size; i++) {
            builder.addArc(i, i + 1);
        }
        builder.addArc(size, 1);
        return builder.build();
    }

    private static Graph generateFork(final int size) {
        final Graph.Builder builder = Graph.builder();
        for(int i = 2; i <= size; i++) {
            builder.addArc(1, i);
        }
        return builder.build();
    }

    @Test
    // A large cycle
    public void testSccDescendingSizes6() {
        final int size = 10_000;
        final Graph graph = generateCycle(size);

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes, equalTo(ImmutableList.of(size)));
    }


    @Test
    // A large cycle
    public void testSccDescendingSizes7() {
        final int size = 10_000;
        final Graph graph = generateFork(size);

        final List<Integer> sccSizes = Sccs.sccDescendingSizes(graph);

        assertThat(sccSizes.size(), equalTo(size));
    }


}