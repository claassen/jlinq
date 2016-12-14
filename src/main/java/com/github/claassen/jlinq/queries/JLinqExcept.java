package com.github.claassen.jlinq.queries;

import java.util.Iterator;
import java.util.function.Predicate;

public class JLinqExcept<T> extends JLinqWhere<T> {

    public JLinqExcept(Predicate<T> condition, Iterator<T> source) {
        super((x) -> !condition.test(x), source);
    }
}
