package com.github.claassen.jlinq;

import com.github.claassen.jlinq.helpers.TestClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinqCollection.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqMapTest {

    @Test
    public void testMap() {
        List<TestClass> items = Arrays.asList(
            new TestClass(1, 2),
            new TestClass(3, 4),
            new TestClass(5, 6)
        );

        JLinqMap<TestClass, Integer> map = query(items).map(x -> x.getX() + x.getY());

        List<Integer> result = map.toList();

        assertThat(result.size(), equalTo(3));
        assertThat(result.get(0), equalTo(3));
        assertThat(result.get(1), equalTo(7));
        assertThat(result.get(2), equalTo(11));
    }
}
