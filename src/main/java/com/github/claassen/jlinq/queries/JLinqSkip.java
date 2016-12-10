package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;

import java.util.Iterator;

public class JLinqSkip<T> extends JLinqBase<T> {

    private int taken = 0;

    public JLinqSkip(int count, Iterator<T> source) {
        if(count < 0) {
            throw new IllegalArgumentException("Skip count cannot be negative");
        }

        setNext(() -> {
            while(taken++ < count) {
                source.next();
            }

            return source.next();
        });

        setHasNext(() -> {
            while(source.hasNext() && taken++ < count) {
                source.next();
            }

            return source.hasNext();
        });
    }
}
