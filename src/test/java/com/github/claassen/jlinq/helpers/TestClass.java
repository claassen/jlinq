package com.github.claassen.jlinq.helpers;

public class TestClass {
    private Integer x;
    private Integer y;

    public TestClass(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (this.x + "~" + this.y).hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof TestClass &&
                ((TestClass) other).getX().equals(this.getX()) &&
                ((TestClass) other).getY().equals(this.getY());
    }
}
