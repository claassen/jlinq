package com.github.claassen.jlinq;

import java.util.Collection;
import java.util.Iterator;

public class JLinqNumericCollection<T extends Number> extends JLinqNumericBase<T> {

    public JLinqNumericCollection(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();

        setNext(() -> iterator.next());
        setHasNext(() -> iterator.hasNext());
    }

    public static <U extends Number> JLinqNumericCollection<U> queryn(Collection<U> collection) {
        return new JLinqNumericCollection<>(collection);
    }
}