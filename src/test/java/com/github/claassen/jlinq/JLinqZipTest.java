package com.github.claassen.jlinq;

import com.github.claassen.jlinq.queries.JLinqZip;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.claassen.jlinq.JLinq.query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class JLinqZipTest {

    @Test
    public void testZip() {
        List<Integer> items1 = Arrays.asList(1, 2, 3);
        List<Integer> items2 = Arrays.asList(4, 5, 6);
        List<Integer> items3 = Arrays.asList(7, 8, 9);

        JLinqZip<Integer> zip = query(items1).zip(0, items2, items3);

        List<Integer> zipped = zip.toList();

        assertThat(zipped.size(), equalTo(9));
        assertThat(zipped.get(0), equalTo(1));
        assertThat(zipped.get(1), equalTo(4));
        assertThat(zipped.get(2), equalTo(7));
        assertThat(zipped.get(3), equalTo(2));
        assertThat(zipped.get(4), equalTo(5));
        assertThat(zipped.get(5), equalTo(8));
        assertThat(zipped.get(6), equalTo(3));
        assertThat(zipped.get(7), equalTo(6));
        assertThat(zipped.get(8), equalTo(9));
    }

    @Test
    public void testZipWithOffset() {
        List<Integer> items1 = Arrays.asList(1, 2, 3);
        List<Integer> items2 = Arrays.asList(4, 5, 6);
        List<Integer> items3 = Arrays.asList(7, 8, 9);

        JLinqZip<Integer> zip = query(items1).zip(1, items2, items3);

        List<Integer> zipped = zip.toList();

        assertThat(zipped.size(), equalTo(9));
        assertThat(zipped.get(0), equalTo(4));
        assertThat(zipped.get(1), equalTo(7));
        assertThat(zipped.get(2), equalTo(1));
        assertThat(zipped.get(3), equalTo(5));
        assertThat(zipped.get(4), equalTo(8));
        assertThat(zipped.get(5), equalTo(2));
        assertThat(zipped.get(6), equalTo(6));
        assertThat(zipped.get(7), equalTo(9));
        assertThat(zipped.get(8), equalTo(3));
    }

    @Test
    public void testZipWithOffsetIsLastSource() {
        List<Integer> items1 = Arrays.asList(1, 2, 3);
        List<Integer> items2 = Arrays.asList(4, 5, 6);
        List<Integer> items3 = Arrays.asList(7, 8, 9);

        JLinqZip<Integer> zip = query(items1).zip(2, items2, items3);

        List<Integer> zipped = zip.toList();

        assertThat(zipped.size(), equalTo(9));
        assertThat(zipped.get(0), equalTo(7));
        assertThat(zipped.get(1), equalTo(1));
        assertThat(zipped.get(2), equalTo(4));
        assertThat(zipped.get(3), equalTo(8));
        assertThat(zipped.get(4), equalTo(2));
        assertThat(zipped.get(5), equalTo(5));
        assertThat(zipped.get(6), equalTo(9));
        assertThat(zipped.get(7), equalTo(3));
        assertThat(zipped.get(8), equalTo(6));
    }

    @Test
    public void testDifferentSizes() {
        List<Integer> items1 = Arrays.asList(1, 2);
        List<Integer> items2 = Arrays.asList(3, 4, 5, 6);
        List<Integer> items3 = Arrays.asList(7, 8, 9);

        JLinqZip<Integer> zip = query(items1).zip(0, items2, items3);

        List<Integer> zipped = zip.toList();

        assertThat(zipped.size(), equalTo(9));
        assertThat(zipped.get(0), equalTo(1));
        assertThat(zipped.get(1), equalTo(3));
        assertThat(zipped.get(2), equalTo(7));
        assertThat(zipped.get(3), equalTo(2));
        assertThat(zipped.get(4), equalTo(4));
        assertThat(zipped.get(5), equalTo(8));
        assertThat(zipped.get(6), equalTo(5));
        assertThat(zipped.get(7), equalTo(9));
        assertThat(zipped.get(8), equalTo(6));
    }

    @Test
    public void testEmpty() {
        List<Integer> items1 = new ArrayList<>();
        List<Integer> items2 = new ArrayList<>();
        List<Integer> items3 = new ArrayList<>();

        JLinqZip<Integer> zip = query(items1).zip(0, items2, items3);

        List<Integer> zipped = zip.toList();

        assertThat(zipped.size(), equalTo(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOffsetNegative() {
        List<Integer> items1 = Arrays.asList(1, 2, 3);
        List<Integer> items2 = Arrays.asList(4, 5, 6);

        JLinqZip<Integer> zip = query(items1).zip(-1, items2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOffsetGreaterThanNumberOfSources() {
        List<Integer> items1 = Arrays.asList(1, 2, 3);
        List<Integer> items2 = Arrays.asList(4, 5, 6);

        JLinqZip<Integer> zip = query(items1).zip(2, items2);
    }
}
