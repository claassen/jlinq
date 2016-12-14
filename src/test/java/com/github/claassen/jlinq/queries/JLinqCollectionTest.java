package com.github.claassen.jlinq.queries;

import com.github.claassen.jlinq.queries.JLinqCollection;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqCollectionTest {

    @Test
    public void testCollection() {
        List<Integer> items = Arrays.asList(1, 2, 3);

        JLinqCollection<Integer> collection = new JLinqCollection<>(items);

        assertThat(collection.next(), equalTo(1));
        assertThat(collection.next(), equalTo(2));
        assertThat(collection.next(), equalTo(3));
        assertThat(collection.hasNext(), equalTo(false));
    }
}
