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

        /**
         * A solution using two runners.
         *
         * Time complexity: O(N^2), since N + N-1 + N-2 + ... + 1
         * Space complexity: O(1)
         */
        <E> void removeDuplicates2() {
            Node<E> runnerA = (Node<E>) header;

            while (runnerA != trailer) {
                Node<E> runnerB = runnerA.getNext();

                while (runnerB != trailer) {
                    if (runnerB.getElement() == runnerA.getElement()) {
                        runnerB.getPrev().setNext(runnerB.getNext());
                        runnerB.getNext().setPrev(runnerB.getPrev());
                    }
                    runnerB = runnerB.getNext();
                }
                runnerA = runnerA.getNext();
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 1, 1, 1, 1, 2, 3, 2, 2, 3, 3, 4, 4, 1, 1, 1, 0, 6, 7, 8, 9, 6, 7};
        CustomLinkedList list = new CustomLinkedList<>(arr1);

        System.out.println(list);

        list.removeDuplicates2();

        System.out.println(list);

        Integer[] arr2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        CustomLinkedList list2 = new CustomLinkedList(arr2);

        System.out.println(list2);

        list2.removeDuplicates2();
        System.out.println(list2);

    }

}
