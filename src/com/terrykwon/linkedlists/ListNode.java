package com.terrykwon.linkedlists;

/**
 * A simple unidirectional node class for linked lists.
 * For use in CTCI problems.
 */
public class ListNode<E> {

    public E element;
    public ListNode<E> next;

    public ListNode() {
        this(null, null);
    }

    public ListNode(E element, ListNode<E> next) {
        this.element = element;
        this.next = next;
    }
}
