package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqNumericBase;

import java.util.Iterator;
import java.util.function.Function;

public class JLinqNumericMap<T, R extends Number> extends JLinqNumericBase<R> {

    public JLinqNumericMap(Function<T, R> map, Iterator<T> source) {
        setNext(() -> map.apply(source.next()));
        setHasNext(() -> source.hasNext());
    }
}