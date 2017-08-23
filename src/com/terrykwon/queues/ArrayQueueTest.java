package com.terrykwon.queues;


import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class ArrayQueueTest {

    private ArrayQueueTest() {

    }

    @Test
    public void testArrayQueue() throws Exception {
        ArrayQueue<Integer> q = new ArrayQueue<>();

        assertTrue(q.isEmpty());

        q.enqueue(0);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        assertEquals((Integer) 0, q.first());
        assertEquals((Integer) 0, q.dequeue());
        assertEquals((Integer) 1, q.dequeue());
        assertEquals((Integer) 2, q.dequeue());
        assertEquals((Integer) 3, q.dequeue());
        assertEquals((Integer) 4, q.dequeue());
        assertNull(q.dequeue());

        for (int i = 0; i < 15; i++) {
            q.enqueue(i);
        }

        for (int j = 0; j < 13; j++) {
            q.dequeue();
        }

        assertEquals((Integer) 13, q.dequeue());

        q.enqueue(111);
        q.enqueue(222);
        q.enqueue(333);
        q.enqueue(444);
        q.enqueue(555);

        assertEquals((Integer) 14, q.dequeue());
        assertEquals((Integer) 111, q.dequeue());
        assertEquals((Integer) 222, q.dequeue());

    }

}
