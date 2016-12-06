package com.claassen.jlinq;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiPredicate;

public class JLinqJoin<T, U> extends JLinqBase<Tuple<T, U>> {

    private List<T> source1Items;
    private List<U> source2Items;

    private int source1Index = 0;
    private int source2Index = 0;

    private boolean fetched = false;

    private Optional<Tuple<T, U>> peekNext;

    private BiPredicate<T, U> condition;

    public JLinqJoin(BiPredicate<T, U> condition, JLinqBase<T> source1, JLinqBase<U> source2) {
        this.condition = condition;

        setNext(() -> {
            if(!fetched) {
                fetch(source1, source2);
            }

            if(!peekNext.isPresent()) {
                throw new NoSuchElementException();
            }

            Tuple<T, U> result = peekNext.get();

            peekNext = getNext();

            return result;
        });

        setHasNext(() -> {
            if(!fetched) {
                fetch(source1, source2);
            }

            return peekNext.isPresent();
        });
    }

    private Optional<Tuple<T, U>> getNext() {
        while(source1Index < source1Items.size() && source2Index < source2Items.size()) {
            T item1 = source1Items.get(source1Index);
            U item2 = source2Items.get(source2Index);

            source2Index++;

            if(source2Index == source2Items.size()) {
                source1Index++;
                source2Index = 0;
            }

            if(condition.test(item1, item2)) {
                return Optional.of(new Tuple<>(item1, item2));
            }
        }

        return Optional.empty();
    }

    private boolean isMatch(Tuple<T, U> tuple) {
        return tuple != null && condition.test(tuple.getX(), tuple.getY());
    }

    private void fetch(JLinqBase<T> source1, JLinqBase<U> source2) {
        source1Items = source1.toList();
        source2Items = source2.toList();

        peekNext = getNext();

        fetched = true;
    }
}