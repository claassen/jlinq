package com.github.claassen.jlinq;

import java.util.Iterator;

public class JLinqGroup<T, G> extends JLinqBase<T> {

    private G key;

    public JLinqGroup(G key, Iterator<T> items) {
        this.key = key;

        setNext(() -> items.next());
        setHasNext(() -> items.hasNext());
    }

    public G key() {
        return this.key;
    }
}