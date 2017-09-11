package com.terrykwon.trees;

import static com.terrykwon.Utils.printTreeInorder;
import static com.terrykwon.Utils.printTreeRotated;

/**
 * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a
 * binary search tree with minimal height.
 */
public class MinimalTree {

    private MinimalTree() {

    }

    /**
     * Uses a recursive approach where the middle element of the array is inserted as the root,
     * while the middle element of the left subarray becomes the left child, and the middle element of the right
     * subarray becomes the right child.
     *
     * Time complexity: O(N)
     * Space complexity: O(log(N)), due to recursion., but O(N) due to output tree.
     *
     * @param arr an Integer array sorted in increasing order, with no duplicate elements.
     * @return the root of the generated binary search tree.
     */
    private static TreeNode<Integer> treeFromArray(Integer[] arr) {
        TreeNode<Integer> root;

        root = insertMiddle(arr, 0, arr.length - 1);

        return root;
    }

    private static TreeNode<Integer> insertMiddle(Integer[] arr, int start, int end) {
        int middle = (start + end + 1) / 2;

        TreeNode<Integer> root = new TreeNode<>(arr[middle], null, null);

        if (start <= middle - 1) {
            root.leftChild = insertMiddle(arr, start, middle - 1);
        }

        if (middle + 1 <= end) {
            root.rightChild = insertMiddle(arr, middle + 1, end);
        }

        return root;
    }

    public static void main(String[] args) {

        Integer[] a1 = {1, 2, 3, 4, 5, 6, 7};
        TreeNode n1 = treeFromArray(a1);

        Integer[] a2 = {1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode n2 = treeFromArray(a2);

        printTreeInorder(n1);
        System.out.println();
        printTreeRotated(n1);

        System.out.println();

        printTreeInorder(n2);
        System.out.println();
        printTreeRotated(n2);

        Integer[] a3 = {99};
        TreeNode n3 = treeFromArray(a3);

        System.out.println();
        printTreeInorder(n3);

    }
}
