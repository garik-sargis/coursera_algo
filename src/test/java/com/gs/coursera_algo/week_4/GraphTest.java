package com.gs.coursera_algo.week_4;


import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GraphTest {
    @Test
    public void test_single_arc() {
        final Graph graph = Graph.builder().addArc(1, 2).build();
        assertThat("!1 -> 2", graph.outSet(1), contains(2));
        assertThat("!1 -> 2", graph.inSet(2), contains(1));
        assertThat("!vertexes {1, 2}", graph.vertexes(), is(ImmutableSet.of(1, 2)));
    }

    @Test
    public void test_reversed() {
        final Graph graph = Graph.builder().addArc(1, 2).build().reversed();
        assertThat("!2 -> 1", graph.outSet(2), contains(1));
        assertThat("!1 -> 2", graph.inSet(1), contains(2));
        assertThat("!vertexes {1, 2}", graph.vertexes(), is(ImmutableSet.of(1, 2)));
    }
}
