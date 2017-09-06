package com.terrykwon.maps;

import com.terrykwon.trees.Tree;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeMapTest {

    private TreeMapTest() {

    }

    @Test
    public void testSize() throws Exception {
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        tree.put(8, 8);
        tree.put(4, 4);
        tree.put(12, 12);
        tree.put(3, 3);
        tree.put(10, 10);
        tree.put(17, 17);
        tree.put(5, 5);

        assertEquals(7, tree.size());
    }

    @Test
    public void testPut() throws Exception {
        TreeMap<Integer, Integer> tree = createTestTree();

        tree.printRotatedTree();

        Iterable<Integer> values = tree.values();
        Integer[] actual = iterableToArray(values, tree.size());
        Integer[] expected = {3, 4, 5, 8, 10, 12, 17};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDelete() throws Exception {
        TreeMap<Integer, Integer> tree = createTestTree();

        tree.remove(10);
        tree.remove(8);

        tree.printRotatedTree();

        Integer[] actual = iterableToArray(tree.values(), tree.size());
        Integer[] expected = {3, 4, 5, 12, 17};

        assertArrayEquals(expected, actual);
    }

    private Integer[] iterableToArray(Iterable<Integer> iterable, int size) {
        Integer[] arr = new Integer[size];
        int index = 0;

        for (Integer i : iterable) {
            arr[index] = i;
            index++;
        }

        return arr;
    }

    private TreeMap<Integer, Integer> createTestTree() {
        TreeMap<Integer, Integer> tree = new TreeMap<>();

        tree.put(8, 8);
        tree.put(4, 4);
        tree.put(12, 12);
        tree.put(3, 3);
        tree.put(10, 10);
        tree.put(17, 17);
        tree.put(5, 5);

        return tree;
    }
}
