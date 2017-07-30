package com.terrykwon.linkedlists;

/**
 * Find the Kth to last element of a singly linked list.
 */
public class KthToLast {
    private static class CustomLinkedList<E> extends SinglyLinkedList<E> {
        CustomLinkedList(E[] array) {
            for (E item : array) {
                addLast(item);
            }
        }

        /**
         * Solution using two pointers.
         *
         * Time complexity: O(N)
         * Space complexity: O(1)
         */
        E kthToLast(int k) {
            Node<E> first = head;
            Node<E> second = first;

            for (int i = 0; i < k; i++) {
                second = second.getNext();
                if (second == null) { // There must be at least K elements in the list.
                    return null;
                }
            }

            while (second.getNext() != null) {
                second = second.getNext();
                first = first.getNext();
            }

            return first.getElement();
        }

    }

    public static void main(String[] args) {
        CustomLinkedList<Integer> list1 = new CustomLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(list1);

        System.out.println(list1.kthToLast(5));
        System.out.println(list1.kthToLast(4));
        System.out.println(list1.kthToLast(1));
        System.out.println(list1.kthToLast(0));

    }
}
