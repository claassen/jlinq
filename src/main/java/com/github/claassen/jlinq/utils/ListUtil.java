package com.github.claassen.jlinq.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtil {

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> items = new ArrayList<>();

        while(iterator.hasNext()) {
            items.add(iterator.next());
        }

        return items;
    }
}
