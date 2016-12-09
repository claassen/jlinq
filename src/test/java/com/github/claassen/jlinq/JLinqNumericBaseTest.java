package com.github.claassen.jlinq;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqNumericBaseTest {

    private List<Double> makeItems() {
        return Arrays.asList(3.3, 1.1, 4.4, 2.2);
    }

    @Test
    public void testSum() {
        assertThat(new TestJLinqNumericBaseImplementation<>(makeItems()).sum(), equalTo(11.0));
        assertThat(new TestJLinqNumericBaseImplementation<>(new ArrayList<>()).sum(), equalTo(0.0));
    }

    @Test
    public void testAvg() {
        assertThat(new TestJLinqNumericBaseImplementation<>(makeItems()).avg(), equalTo(2.75));
        assertThat(new TestJLinqNumericBaseImplementation<>(new ArrayList<>()).avg(), equalTo(0.0));
    }

    @Test
    public void testMin() {
        assertThat(new TestJLinqNumericBaseImplementation<>(makeItems()).min(), equalTo(1.1));
    }

    @Test(expected = NoSuchElementException.class)
    public void testMinEmpty() {
        assertThat(new TestJLinqNumericBaseImplementation<>(new ArrayList<>()).min(), equalTo(0));
    }

    @Test
    public void testMax() {
        assertThat(new TestJLinqNumericBaseImplementation<>(makeItems()).max(), equalTo(4.4));
    }

    @Test(expected = NoSuchElementException.class)
    public void testMaxEmpty() {
        assertThat(new TestJLinqNumericBaseImplementation<>(new ArrayList<>()).min(), equalTo(0));
    }

    class TestJLinqNumericBaseImplementation<T extends Number> extends JLinqNumericBase<T> {

        public TestJLinqNumericBaseImplementation(Iterable<T> items) {
            Iterator<T> iterator = items.iterator();

            setNext(() -> iterator.next());
            setHasNext(() -> iterator.hasNext());
        }
    }
}
