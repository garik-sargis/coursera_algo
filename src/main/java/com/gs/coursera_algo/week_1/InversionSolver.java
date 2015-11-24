package com.gs.coursera_algo.week_1;

import com.gs.coursera_algo.Solver;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InversionSolver implements Solver {
    @Override
    public void solve(final InputStream input, final PrintStream output) {
        final Scanner scanner = new Scanner(input);
        // Create an empty list
        final List<Integer> xs = new ArrayList<>();
        // Populate the list
        while (scanner.hasNextInt()) {
            final int x = scanner.nextInt();
            xs.add(x);
        }

        final MSortingInversionCounter<Integer> inversionCounter = MSortingInversionCounter.withNaturalOrder();

        // Count Inversions
        final long inversionNum = inversionCounter.countInversions(xs);

        // Output the result
        output.println(inversionNum);
    }
}
