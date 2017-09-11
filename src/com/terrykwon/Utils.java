package com.terrykwon;

import com.terrykwon.linkedlists.ListNode;
import com.terrykwon.trees.TreeNode;

import java.util.ArrayDeque;

/**
 * Util methods for convenience.
 */
public class Utils {

    // Prevent instantiation
    private Utils() {

    }

    // Array Utils

    public static void printArray(char[][] arr) {
        for (char[] row : arr) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(int[][] arr) {
        for (int[] row : arr) {
            for (int c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char[][] generateMatrix(int width, int height) {
        String seed = "abcdefghijklmnopqrstuvwxyz123456789";
        char[][] matrix = new char[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                matrix[r][c] = seed.charAt((width * r + c) % seed.length());
            }
        }

        return matrix;
    }

    // Tree Utils

    public static void printTreeInorder(TreeNode<Integer> root) {
        if (root.leftChild != null) {
            printTreeInorder(root.leftChild);
        }

        System.out.print(root.element + " ");

        if (root.rightChild != null) {
            printTreeInorder(root.rightChild);
        }
    }

    /**
     * Prints a simple representation of the tree that is rotated 90 degrees counterclockwise.
     */
    public static void printTreeRotated(TreeNode<Integer> root) {
        printTreeRotated(root, 0);
    }

    /**
     * Uses inorder traversal to print the rightmost elements first,
     * and tracks the depth with each recursive call.
     */
    private static void printTreeRotated(TreeNode<Integer> root, int depth) {

        if (root.rightChild != null) {
            printTreeRotated(root.rightChild, depth + 1);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("    ");
        }
        builder.append(root.element);
        System.out.println(builder.toString());

        if (root.leftChild != null) {
            printTreeRotated(root.leftChild, depth + 1);
        }
    }

    /**
     * A preorder DFS implemented iteratively using a stack.
     */
    private static <E> void dfs(TreeNode<E> root) {
        ArrayDeque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> current = stack.pop();

            System.out.print(current.element + " ");

            if (current.rightChild != null) {
                stack.push(current.rightChild);
            }

            if (current.leftChild != null) {
                stack.push(current.leftChild);
            }
        }
        System.out.println();
    }

    private static <E> void bfs(TreeNode<E> root) {
        ArrayDeque<TreeNode<E>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.removeFirst();

            System.out.print(current.element + " ");

            if (current.leftChild != null) {
                queue.addLast(current.leftChild);
            }

            if (current.rightChild != null) {
                queue.addLast(current.rightChild);
            }
        }
        System.out.println();
    }

    // LinkedList Utils

    public static <E> void printLinkedList(ListNode<E> head) {
        while (head != null) {
            System.out.print(head.element + " ");
            head = head.next;
        }
    }


}
