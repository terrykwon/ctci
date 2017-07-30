package com.terrykwon.linkedlists;

import java.util.HashSet;

/**
 * Write code to remove duplicates from an unsorted linked list.
 */
public class RemoveDuplicates {

    private static class CustomLinkedList<E> extends DoublyLinkedList<E> {
        CustomLinkedList(E[] arr) {
            for (E item : arr) {
                this.addLast(item);
            }
        }

        /**
         * Tracks unique values in a set.
         *
         * Time complexity: O(N)
         * Space complexity: O(N)
         *
         * Note: generics, subclasses, and nested clases...
         */
        <E> void removeDuplicates() {
            HashSet<E> storedValues = new HashSet<>();

            Node<E> node = (Node<E>) header;

            while (node != trailer) {
                if (storedValues.contains((E) node.getElement())) {
                    Node<E> temp = node;
                    // Delete node
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    node = node.getNext();
                    temp = null;
                } else {
                    storedValues.add((E) node.getElement());
                    node = node.getNext();
                }

            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 1, 1, 1, 1, 2, 3, 2, 2, 3, 3, 4, 4, 1, 1, 1, 0, 6, 7, 8, 9, 6, 7};
        CustomLinkedList list = new CustomLinkedList<>(arr1);

        System.out.println(list);

        list.removeDuplicates();

        System.out.println(list);
    }

}
