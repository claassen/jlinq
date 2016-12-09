package com.github.claassen.jlinq;

import com.github.claassen.jlinq.utils.ListUtil;

import java.util.*;
import java.util.function.*;

/**
 * Base class for all JLinq query objects.
 *
 * @param <T> Type of collection.
 */
public abstract class JLinqBase<T> implements Iterator<T> {

    private Supplier<T> _next;
    private Supplier<Boolean> _hasNext;

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
        return ListUtil.toList(this);
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

    public <U> JLinqJoin<T, U> join(Iterable<U> source2, BiPredicate<T, U> predicate) {
        return new JLinqJoin<>(predicate, this, source2.iterator());
    }

    public JLinqUnion<T> union(Iterable<T> source2) {
        List<Iterator<T>> sources = new ArrayList<>();

        sources.add(this);
        sources.add(source2.iterator());

        return new JLinqUnion<>(sources);
    }

    public <G> JLinqGroupBy<T, G> groupBy(Function<T, G> classifier) {
        return new JLinqGroupBy<>(classifier, this);
    }

    public JLinqTake<T> take(int count) {
        return new JLinqTake<>(count, this);
    }

    public JLinqSkip<T> skip(int count) {
        return new JLinqSkip<>(count, this);
    }

    @SafeVarargs
    public final JLinqZip<T> zip(int start, Iterable<T> ...otherSources) {
        List<Iterator<T>> sources = new ArrayList<>();

        sources.add(this);

        for(Iterable<T> source : otherSources) {
            sources.add(source.iterator());
        }

        return new JLinqZip<>(start, sources);
    }

    //TODO: orderBy, reverse, forEach, get, any,

    //TODO: BigInteger, BigDecimal
}
