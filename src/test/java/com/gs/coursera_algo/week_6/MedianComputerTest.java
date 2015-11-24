package com.gs.coursera_algo.week_6;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class MedianComputerTest {

    @Test(expected = IllegalStateException.class)
    public void test_median_from_empty_throws_exception() {
        final MedianComputer computer = MedianComputer.create();

        computer.getMedian();
    }

    @Test
    public void test_median_from_singleton() {
        final MedianComputer computer = MedianComputer.create();
        final int val = 9;

        computer.add(val);

        Assert.assertThat(computer.getMedian(), Matchers.equalTo(val));
    }

    @Test
    public void test_median_from_2_ascending() {
        final MedianComputer computer = MedianComputer.create();

        computer.add(1);
        computer.add(2);

        Assert.assertThat(computer.getMedian(), Matchers.equalTo(1));
    }

    @Test
    public void test_median_from_2_descending() {
        final MedianComputer computer = MedianComputer.create();

        computer.add(2);
        computer.add(1);

        Assert.assertThat(computer.getMedian(), Matchers.equalTo(1));
    }

    @Test
    public void test_median_from_3_ascending() {
        final MedianComputer computer = MedianComputer.create();

        computer.add(1);
        computer.add(2);
        computer.add(3);

        Assert.assertThat(computer.getMedian(), Matchers.equalTo(2));
    }

    @Test
    public void test_median_from_3_descending() {
        final MedianComputer computer = MedianComputer.create();

        computer.add(3);
        computer.add(2);
        computer.add(1);

        Assert.assertThat(computer.getMedian(), Matchers.equalTo(2));
    }

    @Test
    public void test_median_from_3_unordered() {
        final MedianComputer computer = MedianComputer.create();

        computer.add(1);
        computer.add(3);
        computer.add(2);

        Assert.assertThat(computer.getMedian(), Matchers.equalTo(2));
    }
}
