package com.github.claassen.jlinq;

import com.github.claassen.jlinq.queries.JLinqCollection;
import com.github.claassen.jlinq.queries.JLinqNumericCollection;

public class JLinq {

    public static <U> JLinqCollection<U> query(Iterable<U> collection) {
        return new JLinqCollection<>(collection);
    }

    public static <U extends Number> JLinqNumericCollection<U> queryn(Iterable<U> collection) {
        return new JLinqNumericCollection<>(collection);
    }
}
