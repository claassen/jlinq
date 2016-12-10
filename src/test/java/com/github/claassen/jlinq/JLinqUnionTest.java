package com.github.claassen.jlinq;

import com.github.claassen.jlinq.queries.JLinqUnion;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqUnionTest {

    @Test
    public void testUnion() {
        List<Integer> items1 = Arrays.asList(1, 2, 3);
        List<Integer> items2 = Arrays.asList(4, 5, 6);

        JLinqUnion<Integer> union = query(items1).union(items2);

        List<Integer> combined = union.toList();

        assertThat(combined.size(), equalTo(6));
        assertThat(combined.get(0), equalTo(1));
        assertThat(combined.get(1), equalTo(2));
        assertThat(combined.get(2), equalTo(3));
        assertThat(combined.get(3), equalTo(4));
        assertThat(combined.get(4), equalTo(5));
        assertThat(combined.get(5), equalTo(6));
    }

    @Test
    public void testUnionEmpty() {
        List<Integer> items1 = new ArrayList<>();
        List<Integer> items2 = new ArrayList<>();

        JLinqUnion<Integer> union = query(items1).union(items2);

        List<Integer> combined = union.toList();

        assertThat(combined.size(), equalTo(0));
    }
}
