package com.terrykwon.trees;

import com.terrykwon.Utils;
import com.terrykwon.linkedlists.ListNode;

import java.util.HashMap;

import static com.terrykwon.Utils.printLinkedList;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth.
 */
public class ListOfDepths {

    private ListOfDepths() {

    }

    /**
     * Uses a (depth, node) map to store a LinkedList per depth.
     * Uses a preorder DFS to iterate through all nodes recursively, while passing a depth parameter.
     *
     * Time complexity: O(N)
     * Space complexity: O(log(N)), due to recursion, but O(N) due to output LinkedList.
     */
    private static <E> HashMap<Integer, ListNode<E>> listOfDepths(TreeNode<E> root) {
        HashMap<Integer, ListNode<E>> map = new HashMap<>();

        preorder(root, map, 0);

        return map;
    }

    private static <E> void preorder(TreeNode<E> root, HashMap<Integer, ListNode<E>> map, int depth) {

        insert(map, root.element, depth);

        if (root.rightChild != null) {
            preorder(root.rightChild, map, depth + 1);
        }

        if (root.leftChild != null) {
            preorder(root.leftChild, map, depth + 1);
        }
    }

    /**
     * Appends a node to the LinkedList that represents the given depth.
     *
     * If the LinkedList doesn't exist, inserts a node as the new head.
     */
    private static <E> void insert(HashMap<Integer, ListNode<E>> map, E value, int depth) {
        if (!map.containsKey(depth)) {
            map.put(depth, new ListNode<>(value));
        } else {
            // Insert at front
            ListNode<E> temp = new ListNode<>(value);
            temp.next = map.get(depth);

            // Replace head
            map.put(depth, temp);
        }
    }

    /**
     * Builds a sample tree.
     */
    private static TreeNode<Integer> buildTree() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.leftChild = new TreeNode<>(2);
        root.rightChild = new TreeNode<>(3);
        root.leftChild.leftChild = new TreeNode<>(4);
        root.leftChild.rightChild = new TreeNode<>(5);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(7);
        root.leftChild.leftChild.leftChild = new TreeNode<>(8);
        root.leftChild.leftChild.rightChild = new TreeNode<>(9);
        root.rightChild.rightChild.leftChild = new TreeNode<>(10);
        root.rightChild.rightChild.rightChild = new TreeNode<>(11);

        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> t1 = buildTree();

        Utils.printTreeRotated(t1);

        HashMap<Integer, ListNode<Integer>> m1 = listOfDepths(t1);
        for (Integer depth : m1.keySet()) {
            System.out.print(depth + ": ");
            printLinkedList(m1.get(depth));
            System.out.println();
        }
    }
}
