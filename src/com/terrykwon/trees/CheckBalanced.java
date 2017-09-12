package com.terrykwon.trees;

import com.terrykwon.Utils;

/**
 * Implement a function to check if a binary tree is balanced.
 * In this case, balance means that the height of two subtrees of any root never differ by more than one.
 */
public class CheckBalanced {

    private CheckBalanced() {

    }

    /**
     * Recursively tracks the height of each subtree while also checking for the balancing condition.
     *
     * Time complexity: O(N)
     * Space complexity: O(H), where H is the height of the tree.
     */
    private static <E> boolean checkBalanced(TreeNode<E> root) {
        return (getHeightIfBalanced(root) != -1);
    }

    /**
     * Returns -1 if the subtree rooted at node is unbalanced, or the height if it is balanced.
     * This allows upper nodes to compare the height of left and right subtrees to check for balance,
     * unless it is already found that a subtree is unbalanced, in which case, -1 is propagated upwards.
     */
    private static int getHeightIfBalanced(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int lHeight = getHeightIfBalanced(root.leftChild);
        if (lHeight == -1) {
            return -1;
        }

        int rHeight = getHeightIfBalanced(root.rightChild);
        if (rHeight == -1) {
            return -1;
        }

        if (Math.abs(lHeight - rHeight) > 1) {
            return -1;
        }

        return Math.max(lHeight, rHeight) + 1;
    }

    /**
     * Naive solution that compares the height of the left and right subtree, then does the same recursively
     * for each subtree.
     *
     * Time complexity: O(N * log(N)), since the recursive {@link #height} method is called for every node.
     * Space complexity: O(log(N)^2) //TODO: VERIFY
     * */
    private static <E> boolean checkBalanced2(TreeNode<E> root) {
        if (root == null) {
            return true;
        }

        int lHeight = height(root.leftChild);
        int rHeight = height(root.rightChild);

        if (Math.abs(lHeight - rHeight) > 1) {
            return false;
        }

        return checkBalanced2(root.leftChild) && checkBalanced2(root.rightChild);

    }

    private static <E> int height(TreeNode<E> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(1 + height(root.leftChild), 1 + height(root.rightChild));
    }

    /**
     * Builds a sample tree.
     */
    private static TreeNode<Integer> buildBalancedTree() {
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

        return root;
    }

    /**
     * Builds a sample tree.
     */
    private static TreeNode<Integer> buildUnbalancedTree() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.leftChild = new TreeNode<>(2);
        root.rightChild = new TreeNode<>(3);
        root.leftChild.leftChild = new TreeNode<>(4);
        root.leftChild.rightChild = new TreeNode<>(5);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(7);
        root.leftChild.leftChild.leftChild = new TreeNode<>(8);
        root.leftChild.leftChild.rightChild = new TreeNode<>(9);
        root.leftChild.leftChild.rightChild.leftChild = new TreeNode<>(10);
        root.leftChild.leftChild.rightChild.rightChild = new TreeNode<>(11);

        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> t1 = buildBalancedTree();
        Utils.printTreeRotated(t1);
        System.out.println(height(t1)); // 4
        System.out.println(checkBalanced(t1));

        TreeNode<Integer> t2 = buildUnbalancedTree();
        Utils.printTreeRotated(t2);
        System.out.println(checkBalanced(t2));
    }
}
