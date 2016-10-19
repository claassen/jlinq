package com.claassen.jlinq;

import java.util.function.Predicate;

public class JLinqWhere<T> extends JLinqBase<T> {

    private T peekNext;

    public JLinqWhere(Predicate<T> condition, JLinqBase<T> source) {
        peekNext = getNext(condition, source);

        setNext(() -> {
            T result = peekNext;
            peekNext = getNext(condition, source);

            return result;
        });

        setHasNext(() -> condition.test(peekNext));
    }

    private T getNext(Predicate<T> condition, JLinqBase<T> source) {
        while(source._hasNext.get()) {
            T item = source._next.get();

            if(condition.test(item)) {
                return item;
            }
        }

        return null;
    }
}
