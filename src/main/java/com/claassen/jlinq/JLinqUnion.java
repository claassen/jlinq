package com.claassen.jlinq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JLinqUnion<T> extends JLinqBase<T> {

    private JLinqBase<T> currentSource;

    public JLinqUnion(Iterable<JLinqBase<T>> sources) {
        Iterator<JLinqBase<T>> sourcesIter = sources.iterator();

        currentSource = sourcesIter.hasNext() ? sourcesIter.next() : null;

        setNext(() -> {
            if(currentSource != null && currentSource._hasNext.get()) {
                return currentSource._next.get();
            }
            else if(sourcesIter.hasNext()) {
                currentSource = sourcesIter.next();
                return currentSource._next.get();
            }

            throw new NoSuchElementException();
        });

        setHasNext(() -> {
            if(currentSource != null && currentSource._hasNext.get()) {
                return true;
            }
            else if(sourcesIter.hasNext()) {
                currentSource = sourcesIter.next();
                return currentSource._hasNext.get();
            }

            return false;
        });
    }
}