package com.github.claassen.jlinq.utils;

public class Tuple<X, Y> {

    private X x;
    private Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (x.toString() + "~" + y.toString()).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Tuple &&
                this.x.equals(((Tuple) other).getX()) &&
                this.y.equals(((Tuple) other).getY());
    }
}