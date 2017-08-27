package com.terrykwon.trees;

import com.terrykwon.Position;

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
}
