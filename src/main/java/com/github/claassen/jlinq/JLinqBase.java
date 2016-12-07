package com.github.claassen.jlinq;

import java.util.*;
import java.util.function.*;

import static com.github.claassen.jlinq.JLinqCollection.query;

public abstract class JLinqBase<T> implements Iterator<T> {

    protected Supplier<T> _next;
    protected Supplier<Boolean> _hasNext;

    protected void setNext(Supplier<T> next) {
        this._next = next;
    }

    protected void setHasNext(Supplier<Boolean> hasNext) {
        this._hasNext = hasNext;
    }

    public T next() {
        return _next.get();
    }

    public boolean hasNext() {
        return _hasNext.get();
    }

    public Iterable<T> toIterable() {
        return () -> new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return _hasNext.get();
            }

            @Override
            public T next() {
                return _next.get();
            }
        };
    }

    public List<T> toList() {
        List<T> items = new ArrayList<>();

        while(hasNext()) {
            items.add(next());
        }

        return items;
    }

    public T first() {
        return next();
    }

    public T last() {
        T item = null;

        while(hasNext()) {
            item = next();
        }

        return item;
    }

    public <R> R reduce(R identity, BiFunction<R, T, R> accumulator) {
        R result = identity;

        while(hasNext()) {
            result = accumulator.apply(result, next());
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

    public <U> JLinqJoin<T, U> join(JLinqBase<U> source2, BiPredicate<T, U> predicate) {
        return new JLinqJoin<>(predicate, this, source2);
    }

    public <U> JLinqJoin<T, U> join(Iterable<U> source2, BiPredicate<T, U> predicate) {
        return join(query(source2), predicate);
    }

    public JLinqUnion<T> union(JLinqBase<T> source2) {
        List<JLinqBase<T>> sources = new ArrayList<>();

        sources.add(this);
        sources.add(source2);

        return new JLinqUnion<>(sources);
    }

    public JLinqUnion<T> union(Iterable<T> source2) {
        return union(query(source2));
    }

    public <G> JLinqGroupBy<T, G> groupBy(Function<T, G> classifier) {
        return new JLinqGroupBy(classifier, this);
    }

    public JLinqTake<T> take(int count) {
        return new JLinqTake<>(count, this);
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
