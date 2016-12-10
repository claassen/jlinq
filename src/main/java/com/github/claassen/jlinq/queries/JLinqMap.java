package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;

import java.util.Iterator;
import java.util.function.Function;

public class JLinqMap<T, R> extends JLinqBase<R> {

    public JLinqMap(Function<T, R> map, Iterator<T> source) {
        setNext(() -> map.apply(source.next()));
        setHasNext(() -> source.hasNext());
    }
}