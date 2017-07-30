package com.terrykwon.linkedlists;

/**
 * An implementation of a singly linked list.
 */
public class SinglyLinkedList<E> {

    private Node<E> head = null;
    private Node<E> tail = null; // Keep track of tail to avoid traversing full length when inserting at end.
    private int size = 0; // Keep track of length of list; or else you would need to traverse full length.

    public SinglyLinkedList() {

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

        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }

        return tail.getElement();
    }

    public void addFirst(E element) {
        head = new Node<>(element, head);
        if (isEmpty()) {
            // This is the first element insertion.
            tail = head;
        }
        size++;
    }

    public void addLast(E element) {
        Node<E> node = new Node<>(element, null);
        if (isEmpty()) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    /**
     * Converts the linked list to a string.
     * The element type must either be primitive or have an overridden {@code toString()} method.
     *
     * @return  The string representation of the linked list.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node node = head;

        while (node != null) {
            builder.append(node.element);
            node = node.next;
        }

        return builder.toString();
    }

    /**
     * Removes the first element.
     *
     * @return  the removed element, or null if there are no elements in the list.
     */
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E element = head.getElement();
        head = head.getNext(); // Next can possibly be null.
        size--;

        if (isEmpty()) {
            tail = null;
        }

        return element;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        SinglyLinkedList other = (SinglyLinkedList) obj;

        if (size() != other.size()) {
            return false;
        }

        Node walkA = head;
        Node walkB = other.head;

        while (walkA != null) {
            if (walkA.getElement() != walkB.getElement()) {
                return false;
            }
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }

        return true;
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
