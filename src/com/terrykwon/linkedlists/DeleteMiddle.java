package com.terrykwon.linkedlists;

/**
 * Implement an algorithm to delete a node in the middle of a singly linked list.
 */
public class DeleteMiddle {

    /**
     * Since deletion of the current node requires access to the previous node,
     * move the current node's value to the next node and delete that instead.
     *
     * Time complexity: O(1), or is it O(N) since N = 1 anyways?
     * Space complexity: O(1)
     */
    static <E> void deleteMiddle(SinglyLinkedList.Node<E> node) {
        SinglyLinkedList.Node<E> next = node.getNext();
        node.setElement(next.getElement());
        node.setNext(next.getNext());
        next = null;
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.head = new SinglyLinkedList.Node(0, null);

        SinglyLinkedList.Node<Integer> n1 = new SinglyLinkedList.Node<>(1, null);
        list.head.setNext(n1);

        SinglyLinkedList.Node<Integer> n2 = new SinglyLinkedList.Node<>(2, null);
        n1.setNext(n2);

        SinglyLinkedList.Node<Integer> n3 = new SinglyLinkedList.Node<>(3, null);
        n2.setNext(n3);

        SinglyLinkedList.Node<Integer> n4 = new SinglyLinkedList.Node<>(4, null);
        n3.setNext(n4);

        SinglyLinkedList.Node<Integer> n5 = new SinglyLinkedList.Node<>(5, null);
        n4.setNext(n5);

        System.out.println(list);

        deleteMiddle(n2);

        System.out.println(list);
    }
}
