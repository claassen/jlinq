package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.helpers.TestClass;
import com.github.claassen.jlinq.utils.Tuple;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class JLinqJoinTest {

    @Test
    public void testJoin() {
        List<TestClass> items1 = Arrays.asList(
            new TestClass(1, 1),
            new TestClass(2, 2),
            new TestClass(3, 3),
            new TestClass(4, 4)
        );

        List<TestClass> items2 = Arrays.asList(
            new TestClass(30, 30),
            new TestClass(2, 20),
            new TestClass(40, 40),
            new TestClass(1, 10)
        );

        JLinqJoin<TestClass, TestClass> join = query(items1).join(items2, (x, y) -> x.getX().equals(y.getX()));

        List<Tuple<TestClass, TestClass>> tuples = join.toList();

        assertThat(tuples.size(), equalTo(2));
        assertThat(tuples, hasItems(
            new Tuple<>(items1.get(0), items2.get(3)),
            new Tuple<>(items1.get(1), items2.get(1))
        ));
    }
}
