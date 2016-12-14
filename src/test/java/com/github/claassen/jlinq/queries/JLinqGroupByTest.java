package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.helpers.TestClass;
import com.github.claassen.jlinq.queries.JLinqGroup;
import com.github.claassen.jlinq.queries.JLinqGroupBy;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JLinqGroupByTest {

    @Test
    public void testGroupBy() {
        List<TestClass> items = Arrays.asList(
            new TestClass(1, 1),
            new TestClass(1, 2),
            new TestClass(2, 3),
            new TestClass(2, 4),
            new TestClass(3, 5),
            new TestClass(3, 6)
        );

        JLinqGroupBy<TestClass, Integer> groupBy = query(items).groupBy(x -> x.getX());

        List<JLinqGroup<TestClass, Integer>> groups = groupBy.toList();

        List<TestClass> group1 = groups.get(0).toList();
        List<TestClass> group2 = groups.get(1).toList();
        List<TestClass> group3 = groups.get(2).toList();

        assertThat(group1.size(), equalTo(2));
        assertThat(group1.get(0), is(items.get(0)));
        assertThat(group1.get(1), is(items.get(1)));

        assertThat(group2.size(), equalTo(2));
        assertThat(group2.get(0), is(items.get(2)));
        assertThat(group2.get(1), is(items.get(3)));

        assertThat(group3.size(), equalTo(2));
        assertThat(group3.get(0), is(items.get(4)));
        assertThat(group3.get(1), is(items.get(5)));

        query(items).mapn(x -> new BigDecimal(x.getX())).sum();
    }
}
