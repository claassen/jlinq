package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.base.JLinqBase;
import com.github.claassen.jlinq.utils.ListUtil;
import com.github.claassen.jlinq.utils.Tuple;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiPredicate;

public class JLinqJoin<T, U> extends JLinqBase<Tuple<T, U>> {

    private List<T> source1Items;
    private List<U> source2Items;

    private int source1Index = 0;
    private int source2Index = 0;

    private boolean fetched = false;

    private Tuple<T, U> peekNext;

    private BiPredicate<T, U> condition;

    public JLinqJoin(BiPredicate<T, U> condition, Iterator<T> source1, Iterator<U> source2) {
        this.condition = condition;

        setNext(() -> {
            if(!fetched) {
                fetch(source1, source2);
            }

            if(peekNext == null) {
                throw new NoSuchElementException();
            }

            Tuple<T, U> result = peekNext;

            peekNext = getNext();

            return result;
        });

        setHasNext(() -> {
            if(!fetched) {
                fetch(source1, source2);
            }

            return peekNext != null;
        });
    }

    private Tuple<T, U> getNext() {
        while(source1Index < source1Items.size() && source2Index < source2Items.size()) {
            T item1 = source1Items.get(source1Index);
            U item2 = source2Items.get(source2Index);

            source2Index++;

            if(source2Index == source2Items.size()) {
                source1Index++;
                source2Index = 0;
            }

            if(condition.test(item1, item2)) {
                return new Tuple<>(item1, item2);
            }
        }

        return null;
    }

    private void fetch(Iterator<T> source1, Iterator<U> source2) {
        source1Items = ListUtil.toList(source1);
        source2Items = ListUtil.toList(source2);

        peekNext = getNext();

        fetched = true;
    }
}