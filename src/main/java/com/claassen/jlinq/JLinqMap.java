package com.claassen.jlinq;

import java.util.function.Function;

public class JLinqMap<T, R> extends JLinqBase<R> {

    public JLinqMap(Function<T, R> map, JLinqBase<T> source) {
        setNext(() -> map.apply(source._next.get()));
        setHasNext(() -> source._hasNext.get());
    }
}