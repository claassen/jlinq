package com.github.claassen.jlinq.queries.base;

import com.github.claassen.jlinq.helpers.TestClass;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JLinqBaseTest {

    private List<TestClass> makeItems() {
        return Arrays.asList(
                new TestClass(1, 2),
                new TestClass(2, 4),
                new TestClass(3, 6)
        );
    }

    @Test
    public void testAsIterator() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        Iterator<TestClass> iterator = base;

        assertThat(iterator.next(), is(items.get(0)));
        assertThat(iterator.next(), is(items.get(1)));
        assertThat(iterator.next(), is(items.get(2)));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void testToIterable() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        Iterator<TestClass> iterator = base.toIterable().iterator();

        assertThat(iterator.next(), is(items.get(0)));
        assertThat(iterator.next(), is(items.get(1)));
        assertThat(iterator.next(), is(items.get(2)));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void testToList() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        List<TestClass> itemsToList = base.toList();

        assertThat(itemsToList.size(), equalTo(3));
        assertThat(itemsToList.get(0), is(items.get(0)));
        assertThat(itemsToList.get(1), is(items.get(1)));
        assertThat(itemsToList.get(2), is(items.get(2)));
    }

    @Test
    public void testFirst() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        TestClass first = base.first();

        assertThat(first, is(items.get(0)));
    }

    @Test
    public void testLast() {
        List<TestClass> items = makeItems();

        TestJLinqBaseImplementation<TestClass> base = new TestJLinqBaseImplementation<>(items);

        TestClass last = base.last();

        assertThat(last, is(items.get(2)));
    }

    @Test
    public void testGet() {
        assertThat(new TestJLinqBaseImplementation<>(Arrays.asList(1, 2, 3)).get(0), equalTo(1));
        assertThat(new TestJLinqBaseImplementation<>(Arrays.asList(1, 2, 3)).get(1), equalTo(2));
        assertThat(new TestJLinqBaseImplementation<>(Arrays.asList(1, 2, 3)).get(2), equalTo(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetNoSuchElement() {
        new TestJLinqBaseImplementation<>(Arrays.asList(1, 2, 3)).get(3);
    }

    @Test
    public void testAny() {
        List<TestClass> items = makeItems();

        assertThat(new TestJLinqBaseImplementation<>(items).any(x -> x.getX().equals(2)), equalTo(true));
        assertThat(new TestJLinqBaseImplementation<>(items).any(x -> x.getX().equals(42)), equalTo(false));
    }

    @Test
    public void testReduce() {
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(0, (memo, item) -> memo + item.getX()), equalTo(6));
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(1, (memo, item) -> memo + item.getX()), equalTo(7));
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(0, (memo, item) -> memo + item.getY()), equalTo(12));
        assertThat(new TestJLinqBaseImplementation<>(makeItems()).reduce(1, (memo, item) -> memo + item.getY()), equalTo(13));
    }

    private int forEachTestState;

    @Test
    public void testForEach() {
        forEachTestState = 0;

        new TestJLinqBaseImplementation<>(Arrays.asList(1, 2, 3)).forEach(x -> forEachTestState += x);

        assertThat(forEachTestState, equalTo(6));
    }

    @Test
    public void testForEachEmpty() {
        forEachTestState = 0;

        new TestJLinqBaseImplementation<>(new ArrayList<>()).forEach(x -> forEachTestState = 1);

        assertThat(forEachTestState, equalTo(0));
    }

    class TestJLinqBaseImplementation<T> extends JLinqBase<T> {

        public TestJLinqBaseImplementation(Iterable<T> items) {
            Iterator<T> iterator = items.iterator();

            setNext(() -> iterator.next());
            setHasNext(() -> iterator.hasNext());
        }
    }
}


