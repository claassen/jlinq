package com.claassen.jlinq;

import java.util.*;
import java.util.function.*;

public abstract class JLinqBase<T> {

    protected Supplier<T> _next;
    protected Supplier<Boolean> _hasNext;

    protected void setNext(Supplier<T> next) {
        this._next = next;
    }

    protected void setHasNext(Supplier<Boolean> hasNext) {
        this._hasNext = hasNext;
    }

    public List<T> toList() {
        List<T> items = new ArrayList<>();

        while(_hasNext.get()) {
            items.add(_next.get());
        }

        return items;
    }

    public Iterable<T> toIterable() {
        return () -> new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return hasNext();
            }

            @Override
            public T next() {
                return next();
            }
        };
    }

    public T first() {
        return _next.get();
    }

    public T last() {
        T item = null;

        while(_hasNext.get()) {
            item = _next.get();
        }

        return item;
    }

    public <R> R reduce(R identity, BiFunction<R, T, R> accumulator) {
        R result = identity;

        while(_hasNext.get()) {
            result = accumulator.apply(result, _next.get());
        }

        return result;
    }

    public JLinqWhere<T> where(Predicate<T> predicate) {
        return new JLinqWhere<>(predicate, this);
    }

    public <R> JLinqMap<T, R> map(Function<T, R> map) {
        return new JLinqMap<>(map, this);
    }

    public <R extends Number> JLinqNumericMap<T, R> mapn(Function<T, R> map) {
        return new JLinqNumericMap<>(map, this);
    }

    public <U> JLinqJoin<T, U> join(BiPredicate<T, U> predicate, JLinqBase<U> source2) {
        return new JLinqJoin<>(predicate, this, source2);
    }

    public final JLinqUnion<T> union(JLinqBase<T>... otherSources) {
        List<JLinqBase<T>> sources = new ArrayList<>();

        sources.add(this);

        Collections.addAll(sources, otherSources);

        return new JLinqUnion<>(sources);
    }

//    public JLinqZip<T> zip(JLinqBase<T> ...otherSources) {
//        List<JLinqBase<T>> sources = new ArrayList<>();
//
//        sources.add(this);
//
//        for(JLinqBase<T> source : otherSources) {
//            sources.add(source);
//        }
//
//        return new JLinqZip<>(0, sources);
//    }
}
