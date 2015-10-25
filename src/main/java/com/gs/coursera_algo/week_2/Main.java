package com.gs.coursera_algo.week_2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Receives a new-line-separated list of integers from the standard input and prints the number of inversions to the
 * standard input
 */
public final class Main {
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);
        // Create an empty list
        final ArrayList<Integer> xs = new ArrayList<>();
        // Populate the list
        while (scanner.hasNextInt()) {
            final int x = scanner.nextInt();
            xs.add(x);
        }

        final Comparator<Integer> comparator = Comparator.naturalOrder();

        final QSorter<Integer> qSorter1 = QSorter.with(comparator, PivotPickers.leftmost());
        final QSorter<Integer> qSorter2 = QSorter.with(comparator, PivotPickers.rightmost());
        final QSorter<Integer> qSorter3 = QSorter.with(comparator, PivotPickers.median(comparator));

        // Count Inversions
        final long result1 = qSorter1.sort(new ArrayList<>(xs));
        final long result2 = qSorter2.sort(new ArrayList<>(xs));
        final long result3 = qSorter3.sort(new ArrayList<>(xs));

        // Output the result
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
