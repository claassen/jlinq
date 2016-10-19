package com.claassen.jlinq;

public class JLinqGroup<T, G> extends JLinqBase<T> {

    private G key;

    public JLinqGroup(G key, JLinqBase<T> items) {
        this.key = key;

        setNext(() -> items._next.get());
        setHasNext(() -> items._hasNext.get());
    }

    public G key() {
        return this.key;
    }
}