package com.terrykwon.linkedlists;

/**
 * The tail points to the head instead of null.
 */
public class CircularlyLinkedList<E> {

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }

        return tail.next.element;
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }

        return tail.element;
    }

    /**
     * Rotates the list such that head.next is the head.
     * Subsequently, the original head is now the tail.
     */
    public void rotate() {
        if (tail != null) {
            tail = tail.next;
        }
    }

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element, null);

        if (isEmpty()) {
            tail = newNode;
            newNode.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }

        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);

        if (isEmpty()) {
            tail = newNode;
            newNode.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<E> head = tail.next;
        if (head == tail) {
            // Only 1 element
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;

        return head.element;
    }


    protected static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}
