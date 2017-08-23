package com.terrykwon.linkedlists;

import static org.junit.Assert.*;
import org.testng.annotations.Test;

public class CircularlyLinkedListTest {

    @Test
    public void testCircularlyLinkedList() throws Exception {

        CircularlyLinkedList<Integer> list = new CircularlyLinkedList<>();

        assertEquals(null, list.first());

        list.addFirst(1);
        list.addFirst(0);
        list.addLast(2);
        list.addLast(3);

        assertEquals(4, list.size());
        assertEquals((Integer) 0, list.removeFirst());

        list.rotate();

        assertEquals((Integer) 2, list.removeFirst());
        assertEquals((Integer) 3, list.removeFirst());
        assertEquals((Integer) 1, list.removeFirst());
        assertEquals(null, list.removeFirst());

        assertEquals(0, list.size());
    }
}
