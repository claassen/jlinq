package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBigDecimalBase;
import com.github.claassen.jlinq.queries.base.JLinqNumericBase;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Iterator;
import java.util.function.Function;

public class JLinqBigDecimalMap<T, R extends BigDecimal> extends JLinqBigDecimalBase<R> {

    public JLinqBigDecimalMap(Function<T, R> map, Iterator<T> source, MathContext roundingMode) {
        super(roundingMode);

        setNext(() -> map.apply(source.next()));
        setHasNext(() -> source.hasNext());
    }
}