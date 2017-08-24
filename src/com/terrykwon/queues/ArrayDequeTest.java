package com.terrykwon.queues;

import org.testng.annotations.Test;
import static org.junit.Assert.*;


public class ArrayDequeTest {

    private ArrayDequeTest() {

    }

    @Test
    public void testArrayDeque() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.print();
        assertEquals(0, deque.size());

        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        deque.addFirst(0);

        assertEquals(deque.size(), 5);

        deque.print();

        assertEquals(deque.removeFirst(), (Integer) 0);
        assertEquals(deque.removeLast(), (Integer) 4);

    }

    @Test
    public void testDoubleCapacity() throws Exception {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Move the head to somewhere in the middle.
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);

        for (int i = 4; i < 25; i++) {
            deque.print();
            deque.addLast(i);
        }

        deque.print();

        assertEquals(deque.first(), (Integer) 1);
        assertEquals(deque.last(), (Integer) 24);

        for (int j = 0; j > -126; j--) {
            deque.addFirst(j);
        }

        assertEquals(deque.first(), (Integer) (-125));
        assertEquals(deque.last(), (Integer) 24);

        deque.print();
    }

}
