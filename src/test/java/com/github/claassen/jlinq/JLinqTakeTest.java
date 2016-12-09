package com.github.claassen.jlinq;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinqCollection.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqTakeTest {

    @Test
    public void testTake() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        JLinqTake<Integer> take = query(items).take(3);

        List<Integer> taken = take.toList();

        assertThat(taken.size(), equalTo(3));
        assertThat(taken.get(0), equalTo(1));
        assertThat(taken.get(1), equalTo(2));
        assertThat(taken.get(2), equalTo(3));
    }

    @Test
    public void testTakeEqualToLength() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        JLinqTake<Integer> take = query(items).take(5);

        List<Integer> taken = take.toList();

        assertThat(taken.size(), equalTo(5));
        assertThat(taken.get(0), equalTo(1));
        assertThat(taken.get(1), equalTo(2));
        assertThat(taken.get(2), equalTo(3));
        assertThat(taken.get(3), equalTo(4));
        assertThat(taken.get(4), equalTo(5));
    }

    @Test
    public void testTakeGreaterThanLength() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        JLinqTake<Integer> take = query(items).take(6);

        List<Integer> taken = take.toList();

        assertThat(taken.size(), equalTo(5));
        assertThat(taken.get(0), equalTo(1));
        assertThat(taken.get(1), equalTo(2));
        assertThat(taken.get(2), equalTo(3));
        assertThat(taken.get(3), equalTo(4));
        assertThat(taken.get(4), equalTo(5));
    }
}
