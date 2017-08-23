package com.terrykwon.queues;

import java.util.ArrayDeque;

/**
 * An implementation of a queue using a circular array.
 */
public class ArrayQueue<E> implements Queue<E> {

    private E[] data;
    private int front = 0;
    private int size = 0;

    public ArrayQueue() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E element) throws IllegalStateException {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }

        int pos = (front + size) % data.length;
        data[pos] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        E element = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        return element;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[front];
    }
}
