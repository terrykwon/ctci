package com.terrykwon.lists;

import java.util.Arrays;

/**
 * A list implementation using a dynamically allocated array.
 *
 */
public class ArrayList<E> implements List<E> {

    private static final int INITIAL_CAPACITY =  16;
    private E[] data;
    private int size = 0;

    ArrayList() {
        this(INITIAL_CAPACITY);
    }

    ArrayList(int capacity) {
        data = (E[]) new Object[capacity]; // Safe cast
    }

    // For debug purposes.
    ArrayList(E[] arr) {
        this();

        size = arr.length;

        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
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
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    @Override
    public E set(int i, E element) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = element;
        return temp;
    }

    @Override
    public void add(int i, E element) throws IndexOutOfBoundsException, IllegalStateException {
        checkIndex(i, size + 1);
        growIfNeeded(size + 1);

        if (size == data.length) {
            throw new IllegalStateException("Array is full"); // Should fix this with dynamic array.
        }
        for (int k = size - 1; k >= i; k--) {
            // Shift all elements one cell to the right.
            data[k + 1] = data[k];
        }
        data[i] = element;
        size++;
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        shrinkIfNeeded(size - 1);

        E temp = data[i];

        for (int k = i; k < size - 1; k++) {
            data[k] = data[k + 1];
        }

        data[size - 1] = null;
        size--;

        return temp;
    }

    private void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            System.out.println("Different class");
            return false;
        }

        ArrayList other = (ArrayList) obj;

        if (other.size() != size()) {
            System.out.println("Different size");
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!data[i].equals(other.get(i))) {
                System.out.println("Expected: " + data[i] + ", Got: " + other.get(i));
                return false;
            }
        }

        return true;
    }

    private void growIfNeeded(int newSize) {
        if (newSize > data.length) {
            E[] newData = (E[]) new Object[data.length * 2];
            for (int i = 0; i < size; i ++) {
                newData[i] = data[i];
            }

            E[] temp = data;
            data = newData;
            temp = null;
        }
    }

    private void shrinkIfNeeded(int newSize) {
        if (newSize <= data.length / 4) {
            E[] newData = (E[]) new Object[data.length / 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            E[] temp = data;
            data = newData;
            temp = null;
        }
    }

    protected int capacity() {
        return data.length;
    }
}
