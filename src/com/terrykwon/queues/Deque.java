package com.terrykwon.queues;

/**
 * A double-ended queue
 */
public interface Deque<E> {

    int size();

    boolean isEmpty();

    E first();

    E last();

    void addFirst(E e);

    void addLast(E e);

    E removeFirst();

    E removeLast();
}
