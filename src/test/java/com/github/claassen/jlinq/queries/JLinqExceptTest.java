package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.helpers.TestClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqExceptTest {

    @Test
    public void testExcept() {
        List<TestClass> items = Arrays.asList(
            new TestClass(1, 4),
            new TestClass(2, 5),
            new TestClass(3, 6),
            new TestClass(4, 7)
        );

        JLinqWhere<TestClass> where = query(items).except(x -> x.getX() % 2 == 0);

        List<TestClass> filtered = where.toList();

        assertThat(filtered.size(), equalTo(2));
        assertThat(filtered.get(0), equalTo(items.get(0)));
        assertThat(filtered.get(1), equalTo(items.get(2)));
    }

    @Test
    public void testExceptNoMatches() {
        List<TestClass> items = Arrays.asList(
                new TestClass(1, 4),
                new TestClass(2, 5),
                new TestClass(3, 6),
                new TestClass(4, 7)
        );

        JLinqWhere<TestClass> where = query(items).except(x -> x.getX().equals(42));

        List<TestClass> filtered = where.toList();

        assertThat(filtered.size(), equalTo(4));
    }

    @Test
    public void testExceptEmpty() {
        List<TestClass> items = new ArrayList<>();

        assertThat(query(items).except(x -> true).toList().size(), equalTo(0));
        assertThat(query(items).except(x -> false).toList().size(), equalTo(0));
    }
}
