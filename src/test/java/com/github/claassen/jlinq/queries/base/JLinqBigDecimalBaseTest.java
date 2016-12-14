package com.github.claassen.jlinq.queries.base;

import com.github.claassen.jlinq.queries.base.JLinqBigDecimalBase;
import com.github.claassen.jlinq.queries.base.JLinqNumericBase;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqBigDecimalBaseTest {

    private List<BigDecimal> makeItems() {
        return Arrays.asList(
            new BigDecimal("3.3"),
            new BigDecimal("1.1"),
            new BigDecimal("4.4"),
            new BigDecimal("2.2")
        );
    }

    @Test
    public void testSum() {
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(makeItems()).sum(), equalTo(new BigDecimal("11.0")));
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(new ArrayList<>()).sum(), equalTo(new BigDecimal(0)));
    }

    @Test
    public void testAvg() {
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(makeItems()).avg(), equalTo(new BigDecimal("2.75")));
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(new ArrayList<>()).avg(), equalTo(new BigDecimal(0)));
    }

    @Test
    public void testMin() {
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(makeItems()).min(), equalTo(new BigDecimal("1.1")));
    }

    @Test(expected = NoSuchElementException.class)
    public void testMinEmpty() {
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(new ArrayList<>()).min(), equalTo(new BigDecimal(0)));
    }

    @Test
    public void testMax() {
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(makeItems()).max(), equalTo(new BigDecimal("4.4")));
    }

    @Test(expected = NoSuchElementException.class)
    public void testMaxEmpty() {
        assertThat(new TestJLinqBigDecimalBaseImplementation<>(new ArrayList<>()).min(), equalTo(new BigDecimal(0)));
    }

    class TestJLinqBigDecimalBaseImplementation<T extends BigDecimal> extends JLinqBigDecimalBase<T> {

        public TestJLinqBigDecimalBaseImplementation(Iterable<T> items) {
            super(MathContext.UNLIMITED);

            Iterator<T> iterator = items.iterator();

            setNext(() -> iterator.next());
            setHasNext(() -> iterator.hasNext());
        }
    }
}
