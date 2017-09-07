package com.terrykwon.trees;

/**
 * A node class for use in CTCI problems.
 */
public class TreeNode<E> {
    E element;
    TreeNode<E> leftChild;
    TreeNode<E> rightChild;

    public TreeNode() {
        this(null, null, null);
    }

    public TreeNode(E element) {
        this(element, null, null);
    }

    public TreeNode(E element, TreeNode<E> leftChild, TreeNode<E> rightChild) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

}
