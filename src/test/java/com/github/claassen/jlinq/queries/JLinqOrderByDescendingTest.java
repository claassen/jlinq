package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.helpers.TestClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqOrderByDescendingTest {

    @Test
    public void testOrderBy() {
        List<TestClass> items = Arrays.asList(
            new TestClass(3, 1),
            new TestClass(1, 2),
            new TestClass(4, 3),
            new TestClass(2, 4)
        );

        List<TestClass> ordered = query(items).orderByDescending(x -> x.getX()).toList();

        assertThat(ordered.size(), equalTo(4));
        assertThat(ordered.get(0), equalTo(items.get(2)));
        assertThat(ordered.get(1), equalTo(items.get(0)));
        assertThat(ordered.get(2), equalTo(items.get(3)));
        assertThat(ordered.get(3), equalTo(items.get(1)));
    }

    @Test
    public void testOrderByEmpty() {
        List<TestClass> ordered = query(new ArrayList<TestClass>()).orderByDescending(x -> x.getX()).toList();

        assertThat(ordered.size(), equalTo(0));
    }
}
