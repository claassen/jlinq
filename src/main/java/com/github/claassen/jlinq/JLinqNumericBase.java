package com.github.claassen.jlinq;

import java.util.NoSuchElementException;

public abstract class JLinqNumericBase<T extends Number> extends JLinqBase<T> {

    public double sum() {
        double sum = 0;

        while(hasNext()) {
            sum += next().doubleValue();
        }

        return sum;
    }

    public double avg() {
        double sum = 0;
        int count = 0;

        while(hasNext()) {
            sum += next().doubleValue();
            count++;
        }

        return count != 0 ? sum / count : 0;
    }

    public double min() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        double min = Double.MAX_VALUE;

        while(hasNext()) {
            double value = next().doubleValue();

            if(value < min) {
                min = value;
            }
        }

        return min;
    }

    public double max() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        double max = Double.MIN_VALUE;

        while(hasNext()) {
            double value = next().doubleValue();

            if(value > max) {
                max = value;
            }
        }

        return max;
    }

    //TODO: median, mode
}