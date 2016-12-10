package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;
import com.github.claassen.jlinq.utils.ListUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JLinqReverse<T> extends JLinqBase<T> {

    private Iterator<T> reversed;
    private boolean fetched;

    public JLinqReverse(Iterator<T> source) {
        setNext(() -> {
            if(!fetched) {
                fetch(source);
            }

            return reversed.next();
        });

        setHasNext(() -> {
            if(!fetched) {
                fetch(source);
            }

            return reversed.hasNext();
        });
    }

    private void fetch(Iterator<T> source) {
        List<T> sourceList = ListUtil.toList(source);

        Collections.reverse(sourceList);

        reversed = sourceList.iterator();

        fetched = true;
    }
}
