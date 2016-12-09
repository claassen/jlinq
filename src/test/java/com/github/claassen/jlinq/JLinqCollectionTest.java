package com.github.claassen.jlinq;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinqCollection.query;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqCollectionTest {

    @Test
    public void testCollection() {
        List<Integer> items = Arrays.asList(1, 2, 3);

        JLinqCollection<Integer> collection = new JLinqCollection<>(items);

        assertThat(collection.next(), is(1));
        assertThat(collection.next(), is(2));
        assertThat(collection.next(), is(3));
        assertThat(collection.hasNext(), is(false));
    }
}
