package com.claassen.jlinq;

public class JLinqTake<T> extends JLinqBase<T> {

    private int taken;

    public JLinqTake(int count, JLinqBase<T> source) {
        taken = 0;

        setNext(() -> {
            if(taken < count) {
                taken++;
                return source._next.get();
            }

            return null;
        });

        setHasNext(() -> source._hasNext.get() && taken < count);
    }
}