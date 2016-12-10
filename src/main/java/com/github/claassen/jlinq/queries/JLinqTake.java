package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JLinqTake<T> extends JLinqBase<T> {

    private int taken = 0;

    public JLinqTake(int count, Iterator<T> source) {
        if(count < 0) {
            throw new IllegalArgumentException("Take count cannot be negative");
        }

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