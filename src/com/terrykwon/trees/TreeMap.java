package com.terrykwon.trees;

import com.terrykwon.Entry;
import com.terrykwon.Position;
import com.terrykwon.maps.AbstractSortedMap;

/**
 * A sorted map implementation using a BST.
 */
public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

    public TreeMap() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K key1, K key2) throws IllegalArgumentException {
        return null;
    }

    /**
     * A binary tree subclass that implements methods to balance subtrees via rotations.
     */
    protected static class BalanceableBinaryTree<K, V> extends LinkedBinaryTree<Entry<K, V>> {

        /**
         * Override node factory function to produce a BSTNode rather than a plain Node.
         */
        @Override
        protected Node<Entry<K, V>> createNode(Entry<K, V> e, Node<Entry<K, V>> parent,
                                               Node<Entry<K, V>> left, Node<Entry<K, V>> right) {
            return new BSTNode<>(e, parent, left, right);
        }

        /**
         * Relinks a parent node with its oriented child node.
         *
         * @param asLeftChild if true, makes a left child of the parent. If false, makes a right child.
         */
        private void relink(Node<Entry<K, V>> newParent, Node<Entry<K, V>> newChild, boolean asLeftChild) {
            newChild.setParent(newParent);

            if (asLeftChild) {
                newParent.setLeft(newChild);
            } else {
                newParent.setRight(newChild);
            }
        }

        /**
         * Rotates position p above its parent.
         *
         * @param pivot the pivot node
         */
        public void rotate(Position<Entry<K, V>> pivot) {
            Node<Entry<K, V>> x = validate(pivot);
            Node<Entry<K, V>> y = x.getParent();
            Node<Entry<K, V>> z = y.getParent();

            if (z == null) { // Node has no grandparent
                root = x;
                x.setParent(null);
            } else { // Switch x to be the child of z
                relink(z, x, y == z.getLeft());
            }

            // Rotate x and y
            // Relink x's subtree to the appropriate place in y
            if (x == y.getLeft()) {
                relink(y, x.getRight(), true);
                relink(x, y, false);
            } else {
                relink(y, x.getLeft(), false);
                relink(x, y, true);
            }
        }

        /**
         * A tri-node restructuring method to balance a subtree.
         * @param x the grandchild of the tri-node to restructure.
         * @return the new root of the subtree.
         */
        public Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
            Position<Entry<K, V>> y = parent(x);
            Position<Entry<K, V>> z = parent(y);

            if ((x == right(y)) == (y == right(z))) { // "Straight" alignment
                // Only one rotation required.
                rotate(y);
                return y;
            }

            // Double rotation required.
            rotate(x);
            rotate(x);
            return x;
        }

        public int getAux(Position<Entry<K, V>> p) {
            return ((BSTNode<Entry<K, V>>) p).getAux();
        }

        public void setAux(Position<Entry<K, V>> p, int value) {
            ((BSTNode<Entry<K, V>>) p).setAux(value);
        }

        protected static class BSTNode<E> extends Node<E> {
            int aux = 0;

            public BSTNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
                super(element, parent, left, right);
            }

            public int getAux() {
                return aux;
            }

            public void setAux(int aux) {
                this.aux = aux;
            }
        }
    }

}
