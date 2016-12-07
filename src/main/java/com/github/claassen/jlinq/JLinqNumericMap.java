package com.github.claassen.jlinq;

import java.util.function.Function;

public class JLinqNumericMap<T, R extends Number> extends JLinqNumericBase<R> {

    public JLinqNumericMap(Function<T, R> map, JLinqBase<T> source) {
        setNext(() -> map.apply(source._next.get()));
        setHasNext(() -> source._hasNext.get());
    }
}