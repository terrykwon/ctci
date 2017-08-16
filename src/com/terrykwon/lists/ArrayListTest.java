package com.terrykwon.lists;

import static org.junit.Assert.*;
import org.testng.annotations.Test;


public class ArrayListTest {

    // Prevent instantiation
    private ArrayListTest() {

    }

    @Test
    public void testAdd() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 1);
        list.add(0, 2);
        list.add(0, 3);
        list.add(0, 4);
        list.add(0, 5);
        list.add(0, 6);
        list.add(0, 7);
        list.add(0, 8);
        list.add(0, 9);

        System.out.println(list);

        ArrayList<Integer> ans1 = new ArrayList<>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});

        System.out.println(ans1);

        assertEquals(ans1, list);

    }

    @Test
    public void testRemove() throws Exception {
        ArrayList<Integer> list1 = new ArrayList<>(new Integer[]{0, 1, 2, 3, 4, 5});

        list1.remove(4);
        ArrayList<Integer> ans1 = new ArrayList<>(new Integer[]{0, 1, 2, 3, 5});
        assertEquals(ans1, list1);

        try {
            list1.remove(5);
        } catch (Exception e) {
            assertNotNull(e);
        }

        list1.remove(0);
        list1.remove(0);
        list1.remove(0);
        list1.remove(0);
        ArrayList<Integer> ans2 = new ArrayList<>(new Integer[]{5});
        assertEquals(ans2, list1);
    }

    @Test
    public void testSet() throws Exception {
        ArrayList<Integer> list1 = new ArrayList<>(new Integer[]{0, 1, 2, 3, 4, 5});

        list1.set(4, 128);
        ArrayList<Integer> ans1 = new ArrayList<>(new Integer[]{0, 1, 2, 3, 128, 5});

        System.out.println(list1);
        System.out.println(ans1);

        assert(list1.equals(ans1));
    }

    @Test
    public void testGrow() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            list.add(list.size(), i);
        }

        assertEquals(256, list.capacity());
    }

    @Test
    public void testShrink() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            list.add(list.size(), i);
        }

        for (int j = 0; j < 192; j++) {
            list.remove(0);
        }

        System.out.println(list);

        assertEquals(128, list.capacity());
    }

}
