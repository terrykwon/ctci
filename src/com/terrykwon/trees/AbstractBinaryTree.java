package com.terrykwon.trees;

import com.terrykwon.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements some functions of the BinaryTree interface.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E>
        implements BinaryTree<E> {

    /**
     * Returns an iterable collection of the Positions representing p's children.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>(2);

        if (left(p) != null) {
            snapshot.add(left(p));
        }
        if (right(p) != null) {
            snapshot.add(right(p));
        }

        return snapshot;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Position<E> parent = parent(p);
        if (parent == null) {
            return null; // p is the root
        }

        if (p == left(parent)) {
            return right(parent);
        } else {
            return left(parent);
        }
    }

    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        int count = 0;

        if (left(p) != null) {
            count++;
        }
        if (right(p) != null) {
            count++;
        }

        return count;
    }

    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();

        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);
        }

        return snapshot;
    }

    private void inorderSubtree(Position<E> root, List<Position<E>> snapshot) {
        if (left(root) != null) {
            inorderSubtree(left(root), snapshot);
        }

        snapshot.add(root);

        if (right(root) != null) {
            inorderSubtree(right(root), snapshot);
        }
    }

    /**
     * Prints a simple representation of the tree that is rotated 90 degrees counterclockwise.
     */
    public void printRotated() {
        printRotated(root(), 0);
    }

    /**
     * Uses inorder traversal to print the rightmost elements first,
     * and tracks the depth with each recursive call.
     */
    private void printRotated(Position<E> root, int depth) {

        if (right(root) != null) {
            printRotated(right(root), depth + 1);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("    ");
        }
        builder.append(root.getElement());
        System.out.println(builder.toString());

        if (left(root) != null) {
            printRotated(left(root), depth + 1);
        }

    }
}
