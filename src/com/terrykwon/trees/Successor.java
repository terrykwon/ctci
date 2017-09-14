package com.terrykwon.trees;

/**
 * Find the inorder successor of a given node.
 * Assume that each node has a link to its parent.
 */
public class Successor {

    private Successor() {

    }

    /**
     * The inorder predecessor of a root is the rightmost node of the left subtree.
     * The successor is the leftmode node of the right subtree. Using these principles,
     */
    private static CustomNode successor(CustomNode node) {

        // If node has a right subtree, return leftmost node of right subtree.
        if (node.rightChild != null) {
            node = node.rightChild;
            while (node.leftChild != null) {
                node = node.leftChild;
            }

            return node;
        }

        // node is the root node with no inorder successor
        if (node.parent == null) {
            return null;
        }

        // If node has only a left subtree
        // and node is the left child, then direct its parent is the successor.
        if (node.parent.leftChild == node) {
            return node.parent;
        }

        // If it is the right child, it is larger than the parent.
        // Therefore, it must find the root which houses the node in its left subtree i.e. find the "root"
        // in the "left"-"root"-"right" order of inorder traversal.
        while (node.parent != null && node.parent.rightChild == node) {
            node = node.parent;
        }

        if (node.parent == null) {
            return null;
        }

        while (node.parent != null && node.parent.leftChild == node) {
            node = node.parent;
        }

        return node;


    }

    private static class CustomNode<E> {
        public E value;
        public CustomNode<E> parent = null;
        public CustomNode<E> leftChild = null;
        public CustomNode<E> rightChild = null;

        public CustomNode(E value) {
            this.value = value;
        }

        public CustomNode<E> setLeft(E value) {
            leftChild = new CustomNode<>(value);
            leftChild.parent = this;
            return leftChild;
        }

        public CustomNode<E> setRight(E value) {
            rightChild = new CustomNode<>(value);
            rightChild.parent = this;
            return rightChild;
        }
    }

    public static void main(String[] args) {
        CustomNode<Integer> root = new CustomNode<>(5);
        root.setLeft(2);
        root.setRight(9);
        CustomNode<Integer> n1 = root.leftChild.setLeft(1);
        CustomNode<Integer> n2 = root.leftChild.setRight(4);
        root.rightChild.setLeft(7);
        root.rightChild.setRight(14);
        root.leftChild.rightChild.setLeft(3);
        root.rightChild.leftChild.setRight(8);
        root.rightChild.rightChild.setLeft(12);
        CustomNode<Integer> n5 = root.rightChild.rightChild.setRight(16);

        System.out.println(successor(root)); // null
        System.out.println(successor(n1).value); // 2
        System.out.println(successor(n2).value); // 5
        System.out.println(successor(n5)); // null
    }
}
