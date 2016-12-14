package com.github.claassen.jlinq;

import com.github.claassen.jlinq.queries.JLinqBigDecimalCollection;
import com.github.claassen.jlinq.queries.JLinqCollection;
import com.github.claassen.jlinq.queries.JLinqNumericCollection;
import com.github.claassen.jlinq.queries.base.JLinqBigDecimalBase;

import java.math.BigDecimal;
import java.math.MathContext;

public class JLinq {

    public static <U> JLinqCollection<U> query(Iterable<U> collection) {
        return new JLinqCollection<>(collection);
    }

    public static <U extends Number> JLinqNumericCollection<U> queryn(Iterable<U> collection) {
        return new JLinqNumericCollection<>(collection);
    }

    public static <U extends BigDecimal> JLinqBigDecimalCollection<U> queryd(Iterable<U> collection) {
        return new JLinqBigDecimalCollection<>(collection, MathContext.UNLIMITED);
    }

    public static <U extends BigDecimal> JLinqBigDecimalCollection<U> queryd(Iterable<U> collection, MathContext roundingMode) {
        return new JLinqBigDecimalCollection<>(collection, roundingMode);
    }
}
