package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBigDecimalBase;
import com.github.claassen.jlinq.queries.base.JLinqNumericBase;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Iterator;

public class JLinqBigDecimalCollection<T extends BigDecimal> extends JLinqBigDecimalBase<T> {

    public JLinqBigDecimalCollection(Iterable<T> collection, MathContext roundingMode) {
        super(roundingMode);

        Iterator<T> iterator = collection.iterator();

        setNext(() -> iterator.next());
        setHasNext(() -> iterator.hasNext());
    }
}