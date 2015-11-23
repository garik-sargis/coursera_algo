package com.gs.coursera_algo.week_4;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public enum SccComputer {
    INSTANCE;

    /**
     * @return a list of sizes of the SCCs in descending order
     */
    public List<Integer> sccDescendingSizes(final Graph graph) {
        final List<List<Integer>> sccs = getSccs(graph);
        return sccs.stream()
                .map(List::size)
                .sorted(Comparator.<Integer>reverseOrder())
                .collect(Collectors.toList());
    }

    public List<List<Integer>> getSccs(final Graph graph) {
        // Get the reversed graph
        final Graph revGraph = graph.reversed();
        // Get a list of vertexes sorted by the finishing times of the reversed graph
        final List<Integer> vs = vertexesByFinishingTimes(revGraph);
        return getSccsHelper(graph, Lists.reverse(vs));
    }

    public ImmutableList<Integer> vertexesByFinishingTimes(final Graph graph) {
        final ImmutableList.Builder<Integer> resultBuilder = ImmutableList.builder();

        // The set of explored vertexes
        final Set<Integer> explored = new HashSet<>();

        final Deque<Integer> stack = new LinkedList<>();

        // Iterate over all vertexes
        graph.vertexes().forEach(rootV -> {
            // Push the vertex onto the stack if it's not already explored
            if (!explored.contains(rootV)) {
                explored.add(rootV);
                stack.push(rootV);

                while (!stack.isEmpty()) {
                    // Investigate the vertex at the top of the stack
                    final int v = stack.peek();
                    // The first unexplored out set vertex (if one exists)
                    final Optional<Integer> tOpt = graph.outSet(v)
                            .filter(vert -> !explored.contains(vert))
                            .findAny();
                    if (tOpt.isPresent()) {
                        // Push the unexplored vertex onto the stack
                        // Mark the vertex explored
                        explored.add(tOpt.get());
                        stack.push(tOpt.get());
                    } else {
                        resultBuilder.add(v);
                        stack.pop();
                    }
                }
            }
        });


        return resultBuilder.build();
    }

    /**
     * @param vs a list of vertexes reverse sorted by the finishing times of the reversed graph
     */
    public List<List<Integer>> getSccsHelper(final Graph graph, final Iterable<Integer> vs) {
        final ImmutableList.Builder<List<Integer>> resultBuilder = ImmutableList.builder();

        // The set of explored vertexes
        final Set<Integer> explored = new HashSet<>();

        final Deque<Integer> stack = new LinkedList<>();

        // Iterate over all vertexes
        vs.forEach(rootV -> {
            // Push the vertex onto the stack if it's not already explored
            if (!explored.contains(rootV)) {
                explored.add(rootV);
                stack.push(rootV);

                final ImmutableList.Builder<Integer> sccBuilder = ImmutableList.builder();

                while (!stack.isEmpty()) {
                    // Investigate the vertex at the top of the stack
                    final int v = stack.peek();
                    // The first unexplored out set vertex (if one exists)
                    final Optional<Integer> tOpt = graph.outSet(v)
                            .filter(vert -> !explored.contains(vert))
                            .findAny();
                    if (tOpt.isPresent()) {
                        // Push the unexplored vertex onto the stack
                        // Mark the vertex explored
                        explored.add(tOpt.get());
                        stack.push(tOpt.get());
                    } else {
                        sccBuilder.add(v);
                        stack.pop();
                    }
                }
                // Build the scc and add it to the result list
                final ImmutableList<Integer> scc = sccBuilder.build();
                resultBuilder.add(scc);
            }
        });


        return resultBuilder.build();
    }
}
