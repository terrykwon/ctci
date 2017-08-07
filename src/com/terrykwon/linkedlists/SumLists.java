package com.terrykwon.linkedlists;

import com.terrykwon.linkedlists.SinglyLinkedList.Node;

/**
 * You have two numbers represented by a liknked list, where each node contains a single digit.
 * Write a function that adds the two numbers and returns the node as a linked list.
 */
public class SumLists {

    /**
     * Lowest digit = head.
     *
     * Time complexity: O(N), where N is the length of the node.
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

        // If the node is longer than both n1 and n2
        if (carry == 1) {
            res.addLast(carry);
        }

        return res;
    }

    /**
     * Highest digit = head
     *
     * Uses a (node, carry) tuple.
     *
     * Time complexity: O(N), where N is the length of the node.
     * Space complexity: O(1)
     */
    private static SinglyLinkedList<Integer> sumLists2(SinglyLinkedList<Integer> l1, SinglyLinkedList<Integer> l2) {

        padZeros(l1, l2);

        Node<Integer> n1 = l1.head;
        Node<Integer> n2 = l2.head;

        NodeCarry headNodeCarry = sumNodeCarry(n1, n2);
        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        result.head = headNodeCarry.node;

        if (headNodeCarry.carry > 0) {
            result.addFirst(headNodeCarry.carry);
        }

        return result;
    }

    /**
     * Gets the carry of the next digit node through recursion.
     */
    private static NodeCarry sumNodeCarry(Node<Integer> n1, Node<Integer> n2) {
        int currentSum = n1.getElement() + n2.getElement();
        int carry;

        if (n1.getNext() == null) {
            carry = (n1.getElement() + n2.getElement()) / 10;
            return new NodeCarry(currentSum % 10, carry);
        }

        // Through recursion, gets the node and carry of the next (lower) digit pair.
        NodeCarry nextNodeCarry = sumNodeCarry(n1.getNext(), n2.getNext());

        carry = (currentSum + nextNodeCarry.carry) / 10;

        NodeCarry currentNodeCarry = new NodeCarry((currentSum + nextNodeCarry.carry) % 10, carry);
        currentNodeCarry.node.setNext(nextNodeCarry.node);

        return currentNodeCarry;
    }

    /**
     * Ensures two lists are the same length by padding the shorter list with 0s.
     */
    private static void padZeros(SinglyLinkedList<Integer> l1, SinglyLinkedList<Integer> l2) {
        while (l1.size() < l2.size()) {
            l1.addFirst(0);
        }
        while (l2.size() < l1.size()) {
            l2.addFirst(0);
        }
    }

    /**
     * A tuple to hold a Node and carry.
     */
    private static class NodeCarry {
        Node<Integer> node;
        int carry;

        NodeCarry(int sum, int carry) {
            this.node = new Node<>(sum, null);
            this.carry = carry;
        }
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

        System.out.println();

        System.out.println(sumLists2(l1, l2)); // 1308
        System.out.println(sumLists2(l1, l3)); // 614290
        System.out.println(sumLists2(l4, l5)); // 1 then 0*8
        System.out.println(sumLists2(l6, l6)); // 0

    }
}
