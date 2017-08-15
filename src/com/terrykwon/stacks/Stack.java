package com.terrykwon.stacks;

/**
 * An interface for a LIFO stack.
 */
public interface Stack<E> {

    int size();

    boolean isEmpty();

    /**
     * Inserts an element at th top of the stack.
     * @param element   the element to be inserted.
     */
    void push(E element);

    /**
     * @return  the top element in the stack, or null if empty.
     */
    E top();

    /**
     * Removes the top element in the stack.
     * @return  the removed element, or null if empty.
     */
    E pop();
}
