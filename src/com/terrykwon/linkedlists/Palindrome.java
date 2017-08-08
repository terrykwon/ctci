package com.terrykwon.linkedlists;

import com.terrykwon.linkedlists.SinglyLinkedList.Node;

/**
 * Implement a function to check if a linked list is a palindrome.
 */
public class Palindrome {

    /**
     * Given that the size is known, store the first half of the list in another linked list (a stack).
     * Then the rest of the list should be identical to the stack.
     *
     * Time complexity: O(N)
     * Space complexity: O(N)
     */
    private static <E> boolean isPalindrone(SinglyLinkedList<E> list) {
        if (list.size() < 2) {
            return false;
        }

        SinglyLinkedList<E> firstHalf = new SinglyLinkedList<>();

        Node<E> n1 = list.head;

        // Copy first half of the list.
        for (int i = 0; i < list.size() / 2; i++) {
            firstHalf.addFirst(n1.getElement());
            n1 = n1.getNext();
        }

        Node<E> n2 = firstHalf.head;
        if (list.size() % 2 == 1) {
            // Odd length, so skip the middle element.
            n1 = n1.getNext();
        }

        while (n1 != null) {
            if (n1.getElement() != n2.getElement()) {
                return false;
            }
            n1 = n1.getNext();
            n2 = n2.getNext();
        }

        return true;

    }


    public static void main(String[] args) {

        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 1});
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1});
        SinglyLinkedList<Integer> l3 = new SinglyLinkedList<>(new Integer[]{1, 1});
        SinglyLinkedList<Integer> l4 = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 5, 2});
        SinglyLinkedList<Integer> l5 = new SinglyLinkedList<>(new Integer[]{1});


        System.out.println(isPalindrone(l1)); // true
        System.out.println(isPalindrone(l2)); // true
        System.out.println(isPalindrone(l3)); // true
        System.out.println(isPalindrone(l4)); // false
        System.out.println(isPalindrone(l5)); // false


    }
}
