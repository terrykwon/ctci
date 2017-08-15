package com.terrykwon.lists;


public interface List<E> {

    int size();

    boolean isEmpty();

    /**
     * Returns the element at index i.
     *
     * @param i the index of the element to be fetched.
     * @return  the element at index i.
     * @throws IndexOutOfBoundsException
     */
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at index i with the given element.
     *
     * @param i the index of the element to be replaced.
     * @param element   the new element.
     * @return  the new element.
     * @throws IndexOutOfBoundsException
     */
    E set(int i, E element) throws IndexOutOfBoundsException;

    /**
     * Adds an element at index i, shifting all subsequent elements.
     *
     * @param i the index to insert the new element.
     * @param element   the element to insert.
     * @throws IndexOutOfBoundsException
     */
    void add(int i, E element) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the given index, then shifts all subsequent elements to fill the gap.
     *
     * @param i the index of the element to remove.
     * @return  the removed element.
     * @throws IndexOutOfBoundsException
     */
    E remove(int i) throws IndexOutOfBoundsException;

}
