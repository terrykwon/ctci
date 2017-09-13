package com.terrykwon.trees;

import com.terrykwon.Utils;

/**
 * Validate that a tree is a binary search tree.
 */
public class ValidateBST {

    private ValidateBST() {

    }

    /**
     * If a tree is a BST, inorder traversal should be in ascending order.
     * Keeps track of the preceding element to check ascending order.
     *
     * Drawback: duplicate elements not considered (e.g. if rule is that duplicate elements must go on the left).
     *
     * Time complexity: O(N)
     * Space complexity: O(H)
     */
    private static boolean inorderValidate(TreeNode<Integer> root) {
        return inorderValidate(root, new Integer[]{null});
    }

    private static boolean inorderValidate(TreeNode<Integer> root, Integer[] prevVal) {
        boolean validatedLeft = true;
        boolean validatedRight = true;

        if (root.leftChild != null) {
            validatedLeft = inorderValidate(root.leftChild, prevVal);
        }

        if (prevVal[0] != null && prevVal[0] > root.element) {
            return false;
        }
        // Update previous inorder value.
        prevVal[0] = root.element;
//        System.out.print(prevVal[0] + " ");

        if (root.rightChild != null) {
            validatedRight = inorderValidate(root.rightChild, prevVal);
        }

        return validatedLeft && validatedRight;
    }

    /**
     * Checks left <= root < right, while maintaining min and max values to ensure that all values in the left
     * subtree are smaller than the root, while all values in the right subtree are larger.
     *
     * Time complexity: O(N)
     * Space complexity: O(H)
     */
    private static boolean validateBst(TreeNode<Integer> root) {
        return validateBst(root, null, null);
    }

    private static boolean validateBst(TreeNode<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        // Check that root is between min and max
        if ((min != null && root.element <= min) || (max != null && root.element > max)) {
            return false;
        }

        if (!validateBst(root.leftChild, min, root.element)) {
            return false;
        }

        if (!validateBst(root.rightChild, root.element, max)) {
            return false;
        }

        return true;
    }

    private static TreeNode<Integer> buildBst() {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.leftChild = new TreeNode<>(2);
        root.rightChild = new TreeNode<>(7);
        root.leftChild.leftChild = new TreeNode<>(0);
        root.leftChild.rightChild = new TreeNode<>(3);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(8);
        root.leftChild.leftChild.rightChild = new TreeNode<>(1);

        return root;
    }

    private static TreeNode<Integer> buildNonBst1() {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.leftChild = new TreeNode<>(2);
        root.rightChild = new TreeNode<>(8);
        root.leftChild.leftChild = new TreeNode<>(1);
        root.leftChild.rightChild = new TreeNode<>(3);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(7);

        return root;
    }

    /**
     * (Left, root, right) ordering is correct, but left subtree contains a value greater than root.
     */
    private static TreeNode<Integer> buildNonBst2() {
        TreeNode<Integer> root = new TreeNode<>(5);
        root.leftChild = new TreeNode<>(2);
        root.rightChild = new TreeNode<>(7);
        root.leftChild.leftChild = new TreeNode<>(1);
        root.leftChild.rightChild = new TreeNode<>(10);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(8);

        return root;
    }


    public static void main(String[] args) {
        TreeNode<Integer> t1 = buildBst();
        Utils.printTreeRotated(t1);
        System.out.println(validateBst(t1)); // true
        System.out.println(inorderValidate(t1));

        TreeNode<Integer> t2 = buildNonBst1();
        Utils.printTreeRotated(t2);
        System.out.println(validateBst(t2)); // false
        System.out.println(inorderValidate(t2));

        TreeNode<Integer> t3 = buildNonBst2();
        Utils.printTreeRotated(t3);
        System.out.println(validateBst(t3));
        System.out.println(inorderValidate(t3)); // false
    }
}
