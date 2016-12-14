package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.JLinqSkip;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqSkipTest {

    @Test
    public void testSkip() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        JLinqSkip<Integer> skip = query(items).skip(2);

        List<Integer> skipped = skip.toList();

        assertThat(skipped.size(), equalTo(3));
        assertThat(skipped.get(0), equalTo(3));
        assertThat(skipped.get(1), equalTo(4));
        assertThat(skipped.get(2), equalTo(5));
    }

    @Test
    public void testSkipEqualToLength() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        JLinqSkip<Integer> skip = query(items).skip(5);

        List<Integer> skipped = skip.toList();

        assertThat(skipped.size(), equalTo(0));
    }

    @Test
    public void testSkipGreaterThanLength() {
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);

        JLinqSkip<Integer> skip = query(items).skip(6);

        List<Integer> skipped = skip.toList();

        assertThat(skipped.size(), equalTo(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSkipNegative() {
        query(new ArrayList<>()).skip(-1);
    }
}
