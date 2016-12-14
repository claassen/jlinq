package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.helpers.TestClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqThenByTest {

    @Test
    public void testThenBy() {
        List<TestClass> items = Arrays.asList(
            new TestClass(3, 6),
            new TestClass(1, 2),
            new TestClass(2, 3),
            new TestClass(3, 5),
            new TestClass(1, 1),
            new TestClass(2, 4)
        );

        List<TestClass> ordered = query(items).orderBy(x -> x.getX()).thenBy(x -> x.getY()).toList();

        assertThat(ordered.size(), equalTo(6));
        assertThat(ordered.get(0), equalTo(items.get(4)));
        assertThat(ordered.get(1), equalTo(items.get(1)));
        assertThat(ordered.get(2), equalTo(items.get(2)));
        assertThat(ordered.get(3), equalTo(items.get(5)));
        assertThat(ordered.get(4), equalTo(items.get(3)));
        assertThat(ordered.get(5), equalTo(items.get(0)));
    }
}
