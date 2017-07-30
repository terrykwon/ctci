package com.terrykwon.linkedlists;

import static org.junit.Assert.*;
import org.testng.annotations.Test;

public class SinglyLinkedListTest {

//    public static void main(String[] args) {
//        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
//
//        list.addFirst(1);
//        System.out.println(list);
//        list.addFirst(0);
//        System.out.println(list);
//        list.addLast(2);
//        System.out.println(list);
//        list.addLast(3);
//        System.out.println(list);
//        list.removeFirst();
//        System.out.println(list);
//        list.removeFirst();
//        System.out.println(list);
//        list.removeFirst();
//        System.out.println(list);
//        list.removeFirst();
//        System.out.println(list);
//        list.addFirst(1);
//        System.out.println(list);
//        list.addFirst(0);
//        System.out.println(list);
//        list.addLast(2);
//        System.out.println(list);
//        list.addLast(3);
//        System.out.println(list);
//
//    }

    @Test
    public void testToString() throws Exception {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertEquals("", list.toString());

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        assertEquals("1234", list.toString());
    }

    @Test
    public void testRemoveFirst() throws Exception {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertNull(list.removeFirst());

        list.addFirst(1);
        list.addFirst(2);

        assertEquals(2, (int) list.removeFirst());
        assertEquals(1, (int) list.removeFirst());
    }

    @Test
    public void testFirst() throws Exception {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertNull(list.first());

        list.addFirst(1);
        list.addFirst(2);

        assertEquals(2, (int) list.first());
    }

    @Test
    public void testLast() throws Exception {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertNull(list.last());

        list.addFirst(1);
        list.addFirst(2);

        assertEquals(1, (int) list.last());
    }

    @Test
    public void testEquals() throws Exception {
        SinglyLinkedList<Integer> listA = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> listB = new SinglyLinkedList<>();

        assertEquals(false, listA == listB);

        assertEquals(true, listA.equals(listB));
        assertEquals(true, listB.equals(listA));

        listA.addLast(1);
        listA.addLast(2);
        listB.addLast(1);

        assertEquals(false, listA.equals(listB));
        assertEquals(false, listB.equals(listA));

        listB.addLast(2);

        assertEquals(true, listA.equals(listB));
        assertEquals(true, listB.equals(listA));
    }
}
