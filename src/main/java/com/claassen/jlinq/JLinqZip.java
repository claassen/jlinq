package com.claassen.jlinq;

import java.util.List;

public class JLinqZip<T> extends JLinqBase<T> {

    private int which;

    public JLinqZip(int startWhich, List<JLinqBase<T>> sources) {
        which = startWhich;

        setNext(() -> {
            T item = null;

            while(!sources.get(which++)._hasNext.get()) {
                if(which > sources.size() - 1) {
                    //TODO: throw exception?
                    return null;
                }
            }

            if(which > sources.length - 1) {
                which = 0;
            }
        });
    }
}
