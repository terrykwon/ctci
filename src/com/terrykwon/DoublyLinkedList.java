package com.terrykwon;

/**
 * An implementation of a doubly linked list.
 *
 * Uses sentinel header and trailer nodes for a cleaner implementation.
 * The sentinels ensure that node.getPrev() and node.getNext() never return null; they instead return a
 * node with null values.
 */
public class DoublyLinkedList<E> {
    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
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

        return header.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }

        return trailer.getPrev().getElement(); // Can't you return trailer.element since null anyways?
    }

    private void addBetween(E element, Node<E> predecessor, Node<E> successor) {
        Node<E> node = new Node<>(element, predecessor, successor);
        predecessor.setNext(node);
        successor.setPrev(node);
        size++;
    }

    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();

        predecessor.setNext(successor);
        successor.setPrev(predecessor);

        size --;

        return node.getElement();
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        return remove(header.getNext());
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        return remove(trailer.getPrev());
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
        Node node = header.getNext();

        while (node != trailer) {
            builder.append(node.getElement());
            node = node.getNext();
        }

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        DoublyLinkedList other = (DoublyLinkedList) obj;

        if (size() != other.size()) {
            return false;
        }

        Node walkA = header;
        Node walkB = other.header;

        while (walkA.getNext() != trailer) {
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
        private Node<E> prev;
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
