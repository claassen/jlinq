package com.github.claassen.jlinq;

import java.util.*;
import java.util.function.Function;

public class JLinqGroupBy<T, G> extends JLinqBase<JLinqGroup<T, G>> {

    private Iterator<JLinqGroup<T, G>> iterator;
    private boolean fetched = false;

    public JLinqGroupBy(Function<T, G> classifier, Iterator<T> source) {
        setNext(() -> {
            if(!fetched) {
                fetch(classifier, source);
            }

            return iterator.next();
        });
        setHasNext(() -> {
            if(!fetched) {
                fetch(classifier, source);
            }

            return iterator.hasNext();
        });
    }

    private void fetch(Function<T, G> classifier, Iterator<T> source) {
        Map<G, List<T>> groups = new HashMap<>();

        while(source.hasNext()) {
            T item = source.next();

            G key = classifier.apply(item);

            if(!groups.containsKey(key)) {
                groups.put(key, new ArrayList<T>());
            }

            groups.get(key).add(item);
        }

        List<JLinqGroup<T, G>> jlGroups = new ArrayList<>();

        for(G key : groups.keySet()) {
            jlGroups.add(new JLinqGroup<>(key, new JLinqCollection<T>(groups.get(key))));
        }

        iterator = jlGroups.iterator();

        fetched = true;
    }
}