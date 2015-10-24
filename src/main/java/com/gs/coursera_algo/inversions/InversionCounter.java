package com.gs.coursera_algo.inversions;

import java.util.List;

public interface InversionCounter<T> {
    long countInversions(List<T> xs);
}
