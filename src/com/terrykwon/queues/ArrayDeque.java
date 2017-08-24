package com.terrykwon.queues;

import java.util.Arrays;

/**
 * A deque using a dynamic array implementation.
 */
public class ArrayDeque<E> implements Deque<E> {

    private E[] elements;

    private int head; // Points to first element
    private int tail; // Points to element AFTER last

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        elements = (E[]) new Object[16];
    }


    @Override
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public E first() {
        // This is null if empty
        return elements[head];
    }

    @Override
    public E last() {
        // This is null if empty
        return elements[(tail - 1) & (elements.length - 1)];
    }

    @Override
    public void addFirst(E e) {
        int mask = elements.length - 1;

        head = (head - 1) & mask; // Circular decrement

        elements[head] = e;

        if (head == tail) {
            // Array has reached full capacity.
            doubleCapacity();
        }
    }

    @Override
    public void addLast(E e) {
        int mask = elements.length - 1;

        elements[tail] = e;

        tail = (tail + 1) & mask; // Circular increment

        if (head == tail) {
            // Array has reached full capacity.
            doubleCapacity();
        }
    }

    @Override
    public E removeFirst() {
        E e = elements[head];
        elements[head] = null;
        head = (head + 1) & (elements.length - 1); // Circular increment

        return e;
    }

    @Override
    public E removeLast() {
        tail = (tail - 1) & (elements.length - 1); // Circular decrement
        E e = elements[tail];
        elements[tail] = null;

        return e;
    }

    @SuppressWarnings("unchecked")
    private void doubleCapacity() {
        E[] doubled = (E[]) new Object[elements.length << 1];
        int r = elements.length - head; // Number of elements to the right of head

        System.arraycopy(elements, head, doubled, 0, r);
        System.arraycopy(elements, 0, doubled, r, head);

        head = 0;
        tail = elements.length;
        elements = doubled;
    }

    void print() {
        System.out.println(Arrays.toString(elements));
    }

}