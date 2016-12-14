package com.github.claassen.jlinq.queries.base;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.NoSuchElementException;

public abstract class JLinqBigDecimalBase<T extends BigDecimal> extends JLinqBase<T> {

    private MathContext roundingMode = MathContext.UNLIMITED;

    public JLinqBigDecimalBase(MathContext roundingMode) {
        this.roundingMode = roundingMode;
    }

    public BigDecimal sum() {
        BigDecimal sum = new BigDecimal(0);

        while(hasNext()) {
            sum = sum.add(next());
        }

        return sum;
    }

    public BigDecimal avg() {
        BigDecimal sum = new BigDecimal(0);
        int count = 0;

        while(hasNext()) {
            sum = sum.add(next());
            count++;
        }

        return count != 0 ? sum.divide(new BigDecimal(count), roundingMode) : new BigDecimal(0);
    }

    public BigDecimal min() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        BigDecimal min = null;

        while(hasNext()) {
            BigDecimal value = next();

            if(min == null || value.compareTo(min) < 0) {
                min = value;
            }
        }

        return min;
    }

    public BigDecimal max() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        BigDecimal max = null;

        while(hasNext()) {
            BigDecimal value = next();

            if(max == null || value.compareTo(max) > 0) {
                max = value;
            }
        }

        return max;
    }

    //TODO: median, mode
}