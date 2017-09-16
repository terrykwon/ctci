package com.terrykwon.trees;

import java.util.ArrayDeque;

/**
 * Find the first common ancestor of two nodes in a binary tree.
 */
public class FirstCommonAncestor {

    private FirstCommonAncestor() {

    }

    /**
     * A more efficient implementation that passes n1 or n2 upwards through recursion if found.
     *
     * Time complexity: O(N)
     * Space complexity: O(D)
     */
    private static TreeNode firstCommonAncestor2(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null) {
            return null;
        }

        if (root == n1 && root == n2) {
            return root;
        }

        TreeNode x = firstCommonAncestor2(root.leftChild, n1, n2);
        if (x != null && x != n1 && x != n2) { // Already found ancestor
            return x;
        }

        TreeNode y = firstCommonAncestor2(root.rightChild, n1, n2);
        if (y != null && y != n1 && y != n2) { // Already found ancestor
            return y;
        }

        if (x != null && y != null) { // n1 and n2 found in left and right subtree of root.
            return root;
        } else if (n1 == root || n2 == root) {
            return root;
        } else {
            return x == null ? y : x; // return the non-null value, if available.
        }

    }

    /**
     * Uses a naive approach where a tree is fully traversed to check if n1 and n2 are present; if true, then
     * root's subtrees are traversed. This repeats until a subtree does not contain n1 and n2, meaning that
     * its parent its the first common ancestor.
     *
     * Time complexity: O(N * log(N)) //TODO: OR IS IT N^2??
     * Space complexity: O(2^H), due to the BFS
     */
    private static TreeNode firstCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        TreeNode deepest = null;

        while (!queue.isEmpty()) {
            TreeNode temp = queue.removeFirst(); // FIFO

            if (contains(temp, n1, n2)) {
                deepest = temp;
                if (temp.leftChild != null) {
                    queue.addLast(temp.leftChild);
                }

                if (temp.rightChild != null) {
                    queue.addLast(temp.rightChild);
                }
            }
        }

        return deepest;
    }

    /**
     * Traverses a subtree in a preorder DFS. To check if both n1 and n2 are present.
     */
    private static boolean contains(TreeNode root, TreeNode n1, TreeNode n2) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        boolean containsN1 = false;
        boolean containsN2 = false;

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();

            if (temp == n1) {
                containsN1 = true;
            }

            if (temp == n2) {
                containsN2 = true;
            }

            if (temp.rightChild != null) {
                stack.push(temp.rightChild);
            }

            if (temp.leftChild != null) {
                stack.push(temp.leftChild);
            }

        }

        return containsN1 && containsN2;

    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        TreeNode<Integer> n2 = new TreeNode<>(2);
        root.leftChild = n2;
        root.rightChild = new TreeNode<>(3);
        TreeNode<Integer> n4 = new TreeNode<>(4);
        root.leftChild.leftChild = n4;
        root.leftChild.rightChild = new TreeNode<>(5);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(7);
        root.leftChild.leftChild.leftChild = new TreeNode<>(8);
        root.leftChild.leftChild.rightChild = new TreeNode<>(9);
        TreeNode<Integer> n10 = new TreeNode<>(10);
        root.leftChild.rightChild.leftChild = n10;
        TreeNode<Integer> n11 = new TreeNode<>(11);
        root.rightChild.leftChild.leftChild = n11;

        System.out.println(firstCommonAncestor(root, n4, n10).element); // 2
        System.out.println(firstCommonAncestor2(root, n4, n10).element);
        System.out.println(firstCommonAncestor(root, n10, n11).element); // 1
        System.out.println(firstCommonAncestor2(root, n10, n11).element);



    }
}
