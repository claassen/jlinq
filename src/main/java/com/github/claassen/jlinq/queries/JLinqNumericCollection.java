package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqNumericBase;

import java.util.Iterator;

public class JLinqNumericCollection<T extends Number> extends JLinqNumericBase<T> {

    public JLinqNumericCollection(Iterable<T> collection) {
        Iterator<T> iterator = collection.iterator();

        setNext(() -> iterator.next());
        setHasNext(() -> iterator.hasNext());
    }
}