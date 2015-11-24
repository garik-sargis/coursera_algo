package com.gs.coursera_algo;

import com.google.common.collect.ImmutableMap;
import com.gs.coursera_algo.week_1.InversionSolver;

public enum Main {
    INSTANCE;

    public static void main(final String[] args) {
        INSTANCE.execute(args);
    }

    public void execute(final String[] args) {
        final ImmutableMap<String, Solver> nameToSolver = ImmutableMap.<String, Solver>builder()
                .put("inversions", new InversionSolver())
                .build();

        if (args.length == 0) {
            // Print the names of the available solvers
            nameToSolver.keySet().forEach(System.out::println);
        } else {
            // Get the name of the selected solver (the first argument)
            final String name = args[0];
            // Get the selected solver
            final Solver solver = nameToSolver.get(name);
            // Solve using the standard input
            solver.solve(System.in, System.out);
        }
    }
}
