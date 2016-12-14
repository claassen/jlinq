package com.github.claassen.jlinq.queries;

import java.util.Iterator;
import java.util.function.Function;

public class JLinqOrderByDescending<T, C extends Comparable<C>> extends JLinqOrderBy<T, C> {

    public JLinqOrderByDescending(Function<T, C> compareBy, Iterator<T> source) {
        super((o1, o2) -> -compareBy.apply(o1).compareTo(compareBy.apply(o2)), source);
    }
}
