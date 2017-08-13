package com.terrykwon.linkedlists;

import com.terrykwon.linkedlists.SinglyLinkedList.Node;

import java.util.HashSet;

/**
 * Return the node at the beginning of the loop.
 */
public class LoopDetection {

    /**
     * Simple solution using hash tables to store nodes that were traversed once.
     *
     * Time complexity: O(N)
     * Space complexity: O(N)
     */
    private static <E> Node<E> detectLoop(Node<E> head) {
        if (head == null) {
            return null;
        }

        HashSet<Node<E>> set = new HashSet<>();

        while (head.getNext() != null) {
            set.add(head);

            if (set.contains(head.getNext())) {
                return head;
            }

            head = head.getNext();
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
        n8.setNext(n4);

//        SinglyLinkedList.printList(n1);
        System.out.println(detectLoop(n1).getElement()); // 8
    }
}
