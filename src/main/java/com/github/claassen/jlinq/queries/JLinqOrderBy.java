package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;
import com.github.claassen.jlinq.utils.ListUtil;

import java.util.*;
import java.util.function.Function;

public class JLinqOrderBy<T, C extends Comparable<C>> extends JLinqBase<T> {

    private Iterator<T> source;
    private Comparator<T> comparator;
    private Iterator<T> ordered;
    private boolean fetched = false;

    public JLinqOrderBy(Comparator<T> comparator, Iterator<T> source) {
        this.source = source;
        this.comparator = comparator;

        init();
    }

    public JLinqOrderBy(Function<T, C> compareBy, Iterator<T> source) {
        this.source = source;
        this.comparator = (o1, o2) -> compareBy.apply(o1).compareTo(compareBy.apply(o2));

        init();
    }

    private void init() {
        setNext(() -> {
            if(!fetched) {
                fetch(source);
            }

            return ordered.next();
        });

        setHasNext(() -> {
            if(!fetched) {
                fetch(source);
            }

            return ordered.hasNext();
        });
    }

    public <C2 extends Comparable<C2>> JLinqThenBy<T, C2> thenBy(Function<T, C2> compareBy) {
        return new JLinqThenBy<>(source, comparator, compareBy);
    }

    private void fetch(Iterator<T> source) {
        List<T> sourceList = ListUtil.toList(source);

        Collections.sort(sourceList, comparator);

        ordered = sourceList.iterator();

        fetched = true;
    }
}
