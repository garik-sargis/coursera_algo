package com.gs.coursera_algo.week_4;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Sets;

import java.util.stream.Stream;

public final class Graph {
    public static final class Builder {
        private final ImmutableListMultimap.Builder<Integer, Integer> mOutSetsBuilder;
        private final ImmutableListMultimap.Builder<Integer, Integer> mInSetsBuilder;

        private Builder() {
            mOutSetsBuilder = ImmutableListMultimap.builder();
            mInSetsBuilder = ImmutableListMultimap.builder();
        }

        public Builder addArc(final int v1, final int v2) {
            mOutSetsBuilder.put(v1, v2);
            mInSetsBuilder.put(v2, v1);
            return this;
        }

        public Graph build() {
            final ImmutableListMultimap<Integer, Integer> outSets = mOutSetsBuilder.build();
            final ImmutableListMultimap<Integer, Integer> inSets = mInSetsBuilder.build();
            return new Graph(outSets, inSets);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final ImmutableListMultimap<Integer, Integer> mOutSets;
    private final ImmutableListMultimap<Integer, Integer> mInSets;

    private Graph(final ImmutableListMultimap<Integer, Integer> outSets,
                  final ImmutableListMultimap<Integer, Integer> inSets) {
        mOutSets = outSets;
        mInSets = inSets;
    }

    public Stream<Integer> outSet(final int v) {
        return mOutSets.get(v).stream();
    }

    public Stream<Integer> inSet(final int v) {
        return mInSets.get(v).stream();
    }

    public Stream<Integer> vertexes() {
        return Sets.union(mOutSets.keySet(), mInSets.keySet()).stream();
    }

    public Graph reversed() {
        // Swap in-sets with out-sets
        return new Graph(mInSets, mOutSets);
    }
}
