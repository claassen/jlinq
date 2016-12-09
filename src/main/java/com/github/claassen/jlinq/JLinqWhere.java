package com.github.claassen.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class JLinqWhere<T> extends JLinqBase<T> {

    private Optional<T> peekNext;

    public JLinqWhere(Predicate<T> condition, Iterator<T> source) {
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

    private Optional<T> getNext(Predicate<T> condition, Iterator<T> source) {
        while(source.hasNext()) {
            T item = source.next();

            if(condition.test(item)) {
                return Optional.ofNullable(item);
            }
        }

        return Optional.empty();
    }
}
