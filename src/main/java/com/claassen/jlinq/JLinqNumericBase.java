package com.claassen.jlinq;

public class JLinqNumericBase<T extends Number> extends JLinqBase<T> {

    public double sum() {
        double sum = 0;

        while(_hasNext.get()) {
            sum += _next.get().doubleValue();
        }

        return sum;
    }

    public double avg() {
        double sum = 0;
        int count = 0;

        while(_hasNext.get()) {
            sum += _next.get().doubleValue();
        }

        return count != 0 ? sum / count : 0;
    }

    public double min() {
        double min = Double.MAX_VALUE;

        while(_hasNext.get()) {
            double value = _next.get().doubleValue();

            if(value < min) {
                min = value;
            }
        }

        return min;
    }

    public double max() {
        double min = Double.MAX_VALUE;

        while(_hasNext.get()) {
            double value = _next.get().doubleValue();

            if(value > min) {
                min = value;
            }
        }

        return min;
    }

}