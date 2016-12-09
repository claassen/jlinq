package com.github.claassen.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JLinqTake<T> extends JLinqBase<T> {

    private int taken = 0;

    public JLinqTake(int count, Iterator<T> source) {
        setNext(() -> {
            if(taken < count) {
                taken++;
                return source.next();
            }

            throw new NoSuchElementException();
        });

        setHasNext(() -> source.hasNext() && taken < count);
    }
}