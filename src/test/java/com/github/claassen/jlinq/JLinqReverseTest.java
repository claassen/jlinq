package com.github.claassen.jlinq;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqReverseTest {

    @Test
    public void testReverse() {
        List<Integer> reversed = query(Arrays.asList(1, 2, 3)).reverse().toList();

        assertThat(reversed.size(), equalTo(3));
        assertThat(reversed.get(0), equalTo(3));
        assertThat(reversed.get(1), equalTo(2));
        assertThat(reversed.get(2), equalTo(1));
    }

    @Test
    public void testReverseEmpty() {
        List<Integer> reversed = query(new ArrayList<Integer>()).reverse().toList();

        assertThat(reversed.size(), equalTo(0));
    }
}
