package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;
import com.github.claassen.jlinq.utils.ListUtil;

import java.util.*;
import java.util.function.Function;

public class JLinqOrderBy<T, C extends Comparable<C>> extends JLinqBase<T> {

    private Iterator<T> ordered;
    private boolean fetched = false;

    public JLinqOrderBy(Function<T, C> compareBy, Iterator<T> source) {
        setNext(() -> {
            if(!fetched) {
                fetch(source, compareBy);
            }

            return ordered.next();
        });

        setHasNext(() -> {
            if(!fetched) {
                fetch(source, compareBy);
            }

            return ordered.hasNext();
        });
    }

    private void fetch(Iterator<T> source, Function<T, C> compareBy) {
        List<T> sourceList = ListUtil.toList(source);

        Collections.sort(sourceList, (o1, o2) -> compareBy.apply(o1).compareTo(compareBy.apply(o2)));

        ordered = sourceList.iterator();

        fetched = true;
    }
}
