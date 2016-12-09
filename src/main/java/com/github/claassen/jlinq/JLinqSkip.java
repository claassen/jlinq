package com.github.claassen.jlinq;

import java.util.Iterator;

public class JLinqSkip<T> extends JLinqBase<T> {

    private int taken = 0;

    public JLinqSkip(int count, Iterator<T> source) {
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
