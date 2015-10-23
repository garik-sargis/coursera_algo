package com.gs.coursera_algo.inversions;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Receives a new-line-separated list of integers from the standard input and prints the number of inversions to the
 * standard input
 */
public class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // Create an empty list
        final ArrayList<Integer> xs = new ArrayList<>();
        // Populate the list
        while (scanner.hasNextInt()) {
            final int x = scanner.nextInt();
            xs.add(x);
        }

        // Count Inversions
        final long inversionNum = InversionCounter.countInversions(xs);

        // Output the result
        System.out.println(inversionNum);
    }
}
