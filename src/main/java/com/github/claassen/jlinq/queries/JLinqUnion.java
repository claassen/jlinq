package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JLinqUnion<T> extends JLinqBase<T> {

    private Iterator<T> currentSource;

    public JLinqUnion(Iterable<Iterator<T>> sources) {
        Iterator<Iterator<T>> sourcesIter = sources.iterator();

        currentSource = sourcesIter.hasNext() ? sourcesIter.next() : null;

        setNext(() -> {
            if(currentSource != null && currentSource.hasNext()) {
                return currentSource.next();
            }
            else if(sourcesIter.hasNext()) {
                currentSource = sourcesIter.next();
                return currentSource.next();
            }

            throw new NoSuchElementException();
        });

        setHasNext(() -> {
            if(currentSource != null && currentSource.hasNext()) {
                return true;
            }
            else if(sourcesIter.hasNext()) {
                currentSource = sourcesIter.next();
                return currentSource.hasNext();
            }

            return false;
        });
    }
}