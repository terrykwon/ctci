package com.terrykwon.linkedlists;

/**
 * Write code to partition a linked list around a value X.
 */
public class Partition {

    private static class CustomLinkedList<E> extends SinglyLinkedList<E> {

        CustomLinkedList(E[] arr) {
            super(arr);
        }

        /**
         * Keep track of three sublists: less, equal, and more.
         * Traverse through the original list, then append node to the appropriate sublist.
         *
         * Time complexity: O(N)
         * Space complexity: O(N)
         *
         * Note: a buttload of conditionals... is there another way?
         */
        void partition(int pivot) {
            Node<E> lessStart = null;
            Node<E> lessEnd = null;
            Node<E> equalStart = null;
            Node<E> equalEnd = null;
            Node<E> moreStart = null;
            Node<E> moreEnd = null;

            // Traverse through the original list
            Node<E> node = head;
            while (node != null) {
                Node<E> temp = node;
                node = node.getNext();
                temp.setNext(null);

                if ((Integer) temp.getElement() < pivot) {
                    // Append item to less list
                    if (lessStart == null) {
                        lessStart = temp;
                        lessEnd = temp;
                    } else {
                        lessEnd.setNext(temp);
                        lessEnd = lessEnd.getNext();
                    }
                } else if ((Integer) temp.getElement() == pivot) {
                    // Append item to equal list
                    if (equalStart == null) {
                        equalStart = temp;
                        equalEnd = temp;
                    }
                    else {
                        equalEnd.setNext(temp);
                        equalEnd = equalEnd.getNext();
                    }
                } else {
                    // Append item to more list
                    if (moreStart == null) {
                        moreStart = temp;
                        moreEnd = temp;
                    } else {
                        moreEnd.setNext(temp);
                        moreEnd = moreEnd.getNext();
                    }

                }
            }

            // Append lists if they aren't null.
            // There should be a cleaner way to do this...
            if (lessStart != null) {
                head = lessStart;
                tail = lessEnd;
            }

            if (equalStart != null) {
                if (lessStart == null) {
                    head = equalStart;
                    tail = equalEnd;
                } else {
                    tail.setNext(equalStart);
                    tail = equalEnd;
                }
            }

            if (moreStart != null) {
                if (lessStart == null && equalStart == null) {
                    head = moreStart;
                    tail = moreEnd;
                } else {
                    tail.setNext(moreStart);
                    tail = moreEnd;
                }
            }

        }

    }

    public static void main(String[] args) {
        Integer[] arr1 = new Integer[]{8, 7, 6, 5, 4, 3, 2, 1};
        CustomLinkedList<Integer> list1 = new CustomLinkedList<>(arr1);

        System.out.println(list1);

        list1.partition(4);

        System.out.println(list1);

        list1.partition(8);
        System.out.println(list1);

        list1.partition(0);
        System.out.println(list1);

        Integer[] arr2 = new Integer[]{1};
        CustomLinkedList<Integer> list2 = new CustomLinkedList<>(arr2);
        list2.partition(2);
        System.out.println(list2);

        list2.removeFirst();
        list2.partition(5);
        System.out.println(list2);
    }
}
