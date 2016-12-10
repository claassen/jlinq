package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class JLinqZip<T> extends JLinqBase<T> {

    private int which;

    private Optional<T> peekNext;

    public JLinqZip(int sourceStartIndex, List<Iterator<T>> sources) {
        if(sourceStartIndex < 0) {
            throw new IllegalArgumentException("Source start index cannot be negative");
        }

        if(sourceStartIndex >= sources.size()) {
            throw new IllegalArgumentException("Source start index cannot be greater than number of sources");
        }

        which = sourceStartIndex;

        peekNext = getNext(sources);

        setNext(() -> {
            if(!peekNext.isPresent()) {
                throw new NoSuchElementException();
            }

            T result = peekNext.get();

            peekNext = getNext(sources);

            return result;
        });

        setHasNext(() -> peekNext.isPresent());
    }

    private Optional<T> getNext(List<Iterator<T>> sources) {
        if(which == sources.size()) {
            which = 0;
        }

        int startWhich = which;

        do {
            if(sources.get(which).hasNext()) {
                return Optional.ofNullable(sources.get(which++).next());
            }

            which++;

            if(which == sources.size()) {
                which = 0;
            }
        } while(which != startWhich);

        return Optional.empty();
    }
}
