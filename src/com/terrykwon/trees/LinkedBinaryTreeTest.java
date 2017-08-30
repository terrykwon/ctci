package com.terrykwon.trees;

import com.terrykwon.Position;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class LinkedBinaryTreeTest {

    private LinkedBinaryTreeTest() {

    }

    public static void main(String[] args) {
    }

    @Test
    public void testPreorder() throws Exception {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

        tree.addRoot(0);
        Position<Integer> p1 = tree.addLeft(tree.root(), 1);
        Position<Integer> p2 = tree.addRight(tree.root(), 2);
        Position<Integer> p3 = tree.addLeft(p1, 3);
        Position<Integer> p4 = tree.addRight(p1, 4);
        Position<Integer> p5 = tree.addLeft(p2, 5);
        Position<Integer> p6 = tree.addRight(p2, 6);
        Position<Integer> p7 = tree.addLeft(p5, 7);
        Position<Integer> p8 = tree.addRight(p5, 8);

        Integer[] e1 = {0, 1, 3, 4, 2, 5, 7, 8, 6}; // Expected
        ArrayList<Integer> t1 = new ArrayList<>();
        for (Position<Integer> p : tree.preorder()) {
            t1.add(p.getElement());
        }

        assertArrayEquals(e1, t1.toArray());

    }

    @Test
    public void testPostorder() throws Exception {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

        tree.addRoot(0);
        Position<Integer> p1 = tree.addLeft(tree.root(), 1);
        Position<Integer> p2 = tree.addRight(tree.root(), 2);
        Position<Integer> p3 = tree.addLeft(p1, 3);
        Position<Integer> p4 = tree.addRight(p1, 4);
        Position<Integer> p5 = tree.addLeft(p2, 5);
        Position<Integer> p6 = tree.addRight(p2, 6);
        Position<Integer> p7 = tree.addLeft(p5, 7);
        Position<Integer> p8 = tree.addRight(p5, 8);

        Integer[] e1 = {3, 4, 1, 7, 8, 5, 6, 2, 0}; // Expected
        ArrayList<Integer> t1 = new ArrayList<>();
        for (Position<Integer> p : tree.postorder()) {
            t1.add(p.getElement());
        }

        assertArrayEquals(e1, t1.toArray());

    }
}
