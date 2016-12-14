package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.helpers.TestClass;
import com.github.claassen.jlinq.queries.JLinqWhere;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqWhereTest {

    @Test
    public void testWhere() {
        List<TestClass> items = Arrays.asList(
            new TestClass(1, 4),
            new TestClass(2, 5),
            new TestClass(3, 6),
            new TestClass(4, 7)
        );

        JLinqWhere<TestClass> where = query(items).where(x -> x.getX() % 2 == 0);

        List<TestClass> filtered = where.toList();

        assertThat(filtered.size(), equalTo(2));
        assertThat(filtered.get(0), equalTo(items.get(1)));
        assertThat(filtered.get(1), equalTo(items.get(3)));
    }

    @Test
    public void testWhereNoMatches() {
        List<TestClass> items = Arrays.asList(
                new TestClass(1, 4),
                new TestClass(2, 5),
                new TestClass(3, 6),
                new TestClass(4, 7)
        );

        JLinqWhere<TestClass> where = query(items).where(x -> x.getX().equals(42));

        List<TestClass> filtered = where.toList();

        assertThat(filtered.size(), equalTo(0));
    }

    @Test
    public void testWhereEmpty() {
        List<TestClass> items = new ArrayList<>();

        JLinqWhere<TestClass> where = query(items).where(x -> true);

        List<TestClass> filtered = where.toList();

        assertThat(filtered.size(), equalTo(0));
    }
}
