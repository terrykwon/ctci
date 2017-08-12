package com.terrykwon.linkedlists;

import com.terrykwon.linkedlists.SinglyLinkedList;
import com.terrykwon.linkedlists.SinglyLinkedList.Node;

/**
 * Given two linked lists, determine if the two lists intersect.
 * Note that intersection is defined by reference, not value.
 */
public class Intersection {

    /**
     * If two lists intersect at the Nth from last node, then all nodes after that are equal.
     * Therefore, get the length of the shorter list (call this M), then traverse the last M nodes of the longer list.
     *
     * Time complexity: O(M + N), where M, N are the lengths of each list.
     * You first traverse the length of the longer list to set the start and end pointers,
     * then traverse the length of the shorter list to check equality.
     *
     * Space complexity: O(1)
     */
    private static <E> Node<E> intersection(Node<E> h1, Node<E> h2) {

        if (h1 == null || h2 == null) {
            return null;
        }

        Node<E> start1 = h1;
        Node<E> end1 = h1;
        Node<E> start2 = h2;
        Node<E> end2 = h2;

        while (end1.getNext() != null && end2.getNext() != null) {
            // After this loop, either end1 or end2 is the tail of its respective list.
            end1 = end1.getNext();
            end2 = end2.getNext();
        }

        // Now fix the gap between start and end by moving both.
        while (end1.getNext() != null) {
            end1 = end1.getNext();
            start1 = start1.getNext();
        }

        while (end2.getNext() != null) {
            end2 = end2.getNext();
            start2 = start2.getNext();
        }

        if (end1 != end2) {
            // Since if the lists intersect, they must necessarily have the same tail node.
            return null;
        }

        while (start1 != null) {
            if (start1 == start2) {
                return start1;
            }
            start1 = start1.getNext();
            start2 = start2.getNext();
        }

        return null;
    }

    public static void main(String[] args) {
        Node<Integer> n8 = new Node<>(8, null);
        Node<Integer> n7 = new Node<>(7, n8);
        Node<Integer> n6 = new Node<>(6, n7);
        Node<Integer> n5 = new Node<>(5, n6);
        Node<Integer> n4 = new Node<>(4, n5);
        Node<Integer> n3 = new Node<>(3, n4);
        Node<Integer> n2 = new Node<>(2, n3);
        Node<Integer> n1 = new Node<>(1, n2);

        Node<Integer> m3 = new Node<>(33, n7);
        Node<Integer> m2 = new Node<>(22, m3);
        Node<Integer> m1 = new Node<>(11, m2);

        SinglyLinkedList.printList(n1);
        SinglyLinkedList.printList(m1);

        System.out.println(intersection(n1, m2).getElement()); // 7
    }
}
