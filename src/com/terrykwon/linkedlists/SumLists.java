package com.terrykwon.linkedlists;

import com.terrykwon.linkedlists.SinglyLinkedList.Node;

/**
 * You have two numbers represented by a liknked list, where each node contains a single digit.
 * Write a function that adds the two numbers and returns the sum as a linked list.
 */
public class SumLists {

    /**
     * Lowest digit = head.
     *
     * Time complexity: O(N), where N is the length of the sum.
     * Space complexity: O(1)
     */
    private static SinglyLinkedList sumLists(SinglyLinkedList<Integer> l1, SinglyLinkedList<Integer> l2) {
        Node<Integer> n1 = l1.head;
        Node<Integer> n2 = l2.head;
        int carry = 0;

        SinglyLinkedList<Integer> res = new SinglyLinkedList<>();


        while (n1 != null || n2 != null) {
            int v1 = n1 != null ? n1.getElement() : 0;
            int v2 = n2 != null ? n2.getElement() : 0;
            int digit = carry + v1 + v2;

            if (digit >= 10) {
                carry = 1;
            } else {
                carry = 0;
            }

            res.addLast(digit % 10);

            if (n1 != null) {
                n1 = n1.getNext();
            }

            if (n2 != null) {
                n2 = n2.getNext();
            }
        }

        // If the sum is longer than both n1 and n2
        if (carry == 1) {
            res.addLast(carry);
        }

        return res;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>(new Integer[]{7, 1, 6});
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>(new Integer[]{5, 9, 2});
        SinglyLinkedList<Integer> l3 = new SinglyLinkedList<>(new Integer[]{6, 1, 3, 5, 7, 4});
        SinglyLinkedList<Integer> l4 = new SinglyLinkedList<>(new Integer[]{9, 9, 9, 9, 9, 9, 9, 9});
        SinglyLinkedList<Integer> l5 = new SinglyLinkedList<>(new Integer[]{1});
        SinglyLinkedList<Integer> l6 = new SinglyLinkedList<>(new Integer[]{0});

        System.out.println(sumLists(l1, l2)); // 219
        System.out.println(sumLists(l1, l3)); // 339474
        System.out.println(sumLists(l4, l5)); // 0 * 8 then 1
        System.out.println(sumLists(l6, l6)); // 0

    }
}
