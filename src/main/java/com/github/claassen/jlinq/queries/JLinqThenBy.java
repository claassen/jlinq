package com.github.claassen.jlinq.queries;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;

public class JLinqThenBy<T, C extends Comparable<C>> extends JLinqOrderBy<T, C> {

    public JLinqThenBy(Iterator<T> source, Comparator<T> prevComparator, Function<T, C> compareBy) {
        super((o1, o2) -> {
            int prevCompare = prevComparator.compare(o1, o2);

            if(prevCompare != 0) {
                return prevCompare;
            }

            return compareBy.apply(o1).compareTo(compareBy.apply(o2));
        }, source);
    }
}
