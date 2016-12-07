package com.github.claassen.jlinq;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class JLinqWhere<T> extends JLinqBase<T> {

    private Optional<T> peekNext;

    public JLinqWhere(Predicate<T> condition, JLinqBase<T> source) {
        peekNext = getNext(condition, source);

        setNext(() -> {
            if(!peekNext.isPresent()) {
                throw new NoSuchElementException();
            }

            T result = peekNext.get();

            peekNext = getNext(condition, source);

            return result;
        });

        setHasNext(() -> peekNext.isPresent());
    }

    private Optional<T> getNext(Predicate<T> condition, JLinqBase<T> source) {
        while(source._hasNext.get()) {
            T item = source._next.get();

            if(condition.test(item)) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }
}
