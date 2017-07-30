package com.terrykwon;

import static org.junit.Assert.*;
import org.testng.annotations.Test;


public class DoublyLinkedListTest {

//    public static void main(String[] args) {
//        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
//        System.out.println(list);
//
//        list.addFirst(1);
//        System.out.println(list);
//
//        list.addFirst(0);
//        list.addLast(2);
//        System.out.println(list);
//
//        System.out.println(list.first());
//        System.out.println(list.last());
//    }

    @Test
    public void testToString() throws Exception {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertEquals("", list.toString());

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        assertEquals("1234", list.toString());
    }

    @Test
    public void testRemoveFirst() throws Exception {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertNull(list.removeFirst());

        list.addFirst(1);
        list.addFirst(2);

        assertEquals(2, (int) list.removeFirst());
        assertEquals(1, (int) list.removeFirst());
    }

    @Test
    public void testRemoveLast() throws Exception {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertNull(list.removeLast());

        list.addLast(1);
        list.addLast(2);

        assertEquals(2, (int) list.removeLast());
        assertEquals(1, (int) list.removeLast());
    }

    @Test
    public void testFirst() throws Exception {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertNull(list.first());

        list.addFirst(1);
        list.addFirst(2);

        assertEquals(2, (int) list.first());
    }

    @Test
    public void testLast() throws Exception {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertNull(list.last());

        list.addFirst(1);
        list.addFirst(2);

        assertEquals(1, (int) list.last());
    }

    @Test
    public void testEquals() throws Exception {
        DoublyLinkedList<Integer> listA = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> listB = new DoublyLinkedList<>();

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
