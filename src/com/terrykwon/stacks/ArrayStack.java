package com.terrykwon.stacks;

import com.terrykwon.lists.ArrayList;

/**
 * An implementation of a stack using an arraylist.
 */
public class ArrayStack<E> implements Stack<E>{

    private ArrayList<E> data;

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.size() == 0;
    }

    @Override
    public void push(E element) {
        data.add(element);
    }

    @Override
    public E top() {
        return data.get(data.size() - 1);
    }

    @Override
    public E pop() {
        E top = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return top;
    }
}
