package com.terrykwon.trees;

import com.terrykwon.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract base class providing some functionality of the Tree interface.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        }

        return 1 + depth(parent(p));
    }

    /**
     * Returns the maximum depth of the subtree rooted at p.
     *
     * Behaves similarly to a DFS.
     */
    public int height(Position<E> p) {
        int h = 0;

        for (Position<E> child : children(p)) {
            h = Math.max(h, 1 + height(child));
        }

        // h returns 0 if no children.
        return h;
    }

    /**
     * A preorder traversal of positions (nodes) in the tree.
     */
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();

        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }

        return snapshot;
    }

    private void preorderSubtree(Position<E> root, List<Position<E>> snapshot) {
        snapshot.add(root);

        for (Position<E> child : children(root)) {
            preorderSubtree(child, snapshot);
        }
    }

    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();

        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
        }

        return snapshot;
    }

    private void postorderSubtree(Position<E> root, List<Position<E>> snapshot) {
        for (Position<E> child : children(root)) {
            postorderSubtree(child, snapshot);
        }

        snapshot.add(root);
    }
}
