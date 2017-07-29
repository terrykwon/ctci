package com.terrykwon;

/**
 * Created by terrykwon on 29/07/2017.
 */
public class LinkedListTester {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(1);
        System.out.println(list);
        list.addFirst(0);
        System.out.println(list);
        list.addLast(2);
        System.out.println(list);
        list.addLast(3);
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.removeFirst();
        System.out.println(list);
        list.addFirst(1);
        System.out.println(list);
        list.addFirst(0);
        System.out.println(list);
        list.addLast(2);
        System.out.println(list);
        list.addLast(3);
        System.out.println(list);

    }
}
