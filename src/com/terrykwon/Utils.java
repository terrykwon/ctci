package com.terrykwon;

import com.terrykwon.trees.TreeNode;

/**
 * Util methods for convenience.
 */
public class Utils {

    // Prevent instantiation
    private Utils() {

    }

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

    public static void printInorder(TreeNode<Integer> root) {
        if (root.leftChild != null) {
            printInorder(root.leftChild);
        }

        System.out.print(root.element + " ");

        if (root.rightChild != null) {
            printInorder(root.rightChild);
        }
    }

    /**
     * Prints a simple representation of the tree that is rotated 90 degrees counterclockwise.
     */
    public static void printRotated(TreeNode<Integer> root) {
        printRotated(root, 0);
    }

    /**
     * Uses inorder traversal to print the rightmost elements first,
     * and tracks the depth with each recursive call.
     */
    private static void printRotated(TreeNode<Integer> root, int depth) {

        if (root.rightChild != null) {
            printRotated(root.rightChild, depth + 1);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("    ");
        }
        builder.append(root.element);
        System.out.println(builder.toString());

        if (root.leftChild != null) {
            printRotated(root.leftChild, depth + 1);
        }

    }


}
