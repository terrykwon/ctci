package com.terrykwon.trees;

/**
 * T1 and T2 are two very large binary trees, with T1 much bigger than T2. Determine if T2 is a subtree of T1.
 */
public class CheckSubtree {

    private CheckSubtree() {

    }

    /**
     * For each node in t1, checks if the subtree rooted at that node is identical to t2.
     *
     * Time complexity: O(N * M)
     * Space complexity: O(D), where D is the depth of t2.
     */
    private static <E> boolean checkSubtree(TreeNode<E> t1, TreeNode<E> t2) {

        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null) {
            return false;
        } else if (t2 == null) {
            return false;
        }

        if (t1.element != t2.element) {
            return checkSubtree(t1.leftChild, t2) || checkSubtree(t1.rightChild, t2);
        }

        System.out.println(t1.element);

        // The root elements are the same
        // Check that all children of the two trees are identical as well.
        return checkSubtree(t1.leftChild, t2.leftChild) && checkSubtree(t1.rightChild, t2.rightChild);

    }

    private static TreeNode<Integer> buildTree1() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.leftChild = new TreeNode<>(2);
        root.rightChild = new TreeNode<>(3);
        root.leftChild.leftChild = new TreeNode<>(4);
        root.leftChild.rightChild = new TreeNode<>(5);
        root.rightChild.leftChild = new TreeNode<>(6);
        root.rightChild.rightChild = new TreeNode<>(7);
        root.leftChild.leftChild.leftChild = new TreeNode<>(8);
        root.leftChild.leftChild.rightChild = new TreeNode<>(9);

        return root;
    }

    private static TreeNode<Integer> buildTree2() {
        TreeNode<Integer> root = new TreeNode<>(2);
        root.leftChild = new TreeNode<>(4);
        root.rightChild = new TreeNode<>(5);
        root.leftChild.leftChild = new TreeNode<>(8);
        root.leftChild.rightChild = new TreeNode<>(9);

        return root;
    }

    public static void main(String[] args) {

        TreeNode<Integer> r1 = buildTree1();
        TreeNode<Integer> r2 = buildTree2();

        System.out.println(checkSubtree(r1, r2)); // true

    }
}
