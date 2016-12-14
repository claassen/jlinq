package com.github.claassen.jlinq.queries.base;

import com.github.claassen.jlinq.queries.*;
import com.github.claassen.jlinq.utils.ListUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.*;

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

    public T get(int index) {
        int count = 0;
        T item = null;

        while(hasNext() && count <= index) {
            item = next();
            count++;
        }

        if(count == index + 1) {
            return item;
        }

        throw new NoSuchElementException();
    }

    public boolean any(Predicate<T> predicate) {
        while(hasNext()) {
            if(predicate.test(next())) {
                return true;
            }
        }

        return false;
    }

    public boolean all(Predicate<T> predicate) {
        while(hasNext()) {
            if(!predicate.test(next())) {
                return false;
            }
        }

        return true;
    }

    public <R> R reduce(R identity, BiFunction<R, T, R> accumulator) {
        R result = identity;

        while(hasNext()) {
            result = accumulator.apply(result, next());
        }

        return result;
    }

    public void forEach(Consumer<T> action) {
        while(hasNext()) {
            action.accept(next());
        }
    }

    public JLinqWhere<T> where(Predicate<T> predicate) {
        return new JLinqWhere<>(predicate, this);
    }

    public JLinqExcept<T> except(Predicate<T> predicate) {
        return new JLinqExcept<>(predicate, this);
    }

    public <R> JLinqMap<T, R> map(Function<T, R> map) {
        return new JLinqMap<>(map, this);
    }

    public <R extends Number> JLinqNumericMap<T, R> mapn(Function<T, R> map) {
        return new JLinqNumericMap<>(map, this);
    }

    public <R extends BigDecimal> JLinqBigDecimalMap<T, R> mapd(Function<T, R> map) {
        return new JLinqBigDecimalMap<>(map, this, MathContext.UNLIMITED);
    }

    public <R extends BigDecimal> JLinqBigDecimalMap<T, R> mapd(Function<T, R> map, MathContext roundingMode) {
        return new JLinqBigDecimalMap<>(map, this, roundingMode);
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
    public final JLinqZip<T> zip(int sourceStartIndex, Iterable<T> ...otherSources) {
        List<Iterator<T>> sources = new ArrayList<>();

        sources.add(this);

        for(Iterable<T> source : otherSources) {
            sources.add(source.iterator());
        }

        return new JLinqZip<>(sourceStartIndex, sources);
    }

    public <C extends Comparable<C>> JLinqOrderBy<T, C> orderBy(Function<T, C> compareBy) {
        return new JLinqOrderBy<>(compareBy, this);
    }

    public <C extends Comparable<C>> JLinqOrderByDescending<T, C> orderByDescending(Function<T, C> compareBy) {
        return new JLinqOrderByDescending<>(compareBy, this);
    }

    public JLinqReverse<T> reverse() {
        return new JLinqReverse<>(this);
    }

    //TODO: skipWhile, takeWhile, intersection, compliment (notIn), groupJoin?

    //TODO: BigInteger?
}
