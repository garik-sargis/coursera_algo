package com.gs.coursera_algo.week_4;

import java.util.List;
import java.util.Scanner;

// TODO: Create a single Main class and make the jar executable
public final class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // A graph builder
        final Graph.Builder graphBuilder = Graph.builder();
        // Add arcs
        while (scanner.hasNextInt()) {
            // Read the two vertexes of the arc
            final int v1 = scanner.nextInt();
            final int v2 = scanner.nextInt();
            graphBuilder.addArc(v1, v2);
        }
        // Build the graph
        final Graph graph = graphBuilder.build();

        // Get the list of scc sizes in descending order
        final List<Integer> sccSizes = SccComputer.INSTANCE.sccDescendingSizes(graph);

        // Output the result
        sccSizes.stream().limit(5).forEach(System.out::println);
    }
}
