package com.terrykwon.trees;

import com.terrykwon.Entry;
import com.terrykwon.Position;
import com.terrykwon.maps.AbstractSortedMap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A sorted map implementation using a BST.
 */
public class TreeMap<K, V> extends AbstractSortedMap<K, V> {

    protected BalanceableBinaryTree<K, V> tree = new BalanceableBinaryTree<>();

    public TreeMap() {
        super();
        tree.addRoot(null); // Create a sentinel leaf as root
    }

    public TreeMap(Comparator<K> comparator) {
        super(comparator);
        tree.addRoot(null);
    }

    @Override
    public int size() {
        return tree.size() / 2;
    } // Only internal nodes have entries

    private void expandExternal(Position<Entry<K, V>> p, Entry<K, V> e) {
        tree.set(p, e);
        tree.addLeft(p, null);
        tree.addRight(p, null); // Add new sentinel nodes as left and right children.
    }

    /**
     * Searches the subtree rooted at {@param root} for the given key.
     *
     * @return external (null) node if not found.
     */
    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> root, K key) {
        if (tree.isExternal(root)) {
            return root;
        }

        int comp = compare(key, root.getElement());

        if (comp == 0) {
            return root;
        } else if (comp < 0) {
            return treeSearch(tree.left(root), key);
        } else {
            return treeSearch(tree.right(root), key);
        }
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        checkKey(key); // Checks if key is valid
        Position<Entry<K, V>> p = treeSearch(tree.root(), key);

        // Does nothing in this implementation.
        rebalanceAccess(p);

        if (tree.isExternal(p)) {
            return null; // Since all external leaves are sentinel nulls.
        }

        return p.getElement().getValue();
    }

    /**
     * @return the previous value associated with the key.
     */
    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        checkKey(key);

        Entry<K, V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K, V>> p = treeSearch(tree.root(), key);

        if (tree.isExternal(p)) { // Key is not in the tree.
            expandExternal(p, newEntry);
            rebalanceInsert(p);
            return null;
        }

        V temp = p.getElement().getValue();
        tree.set(p, newEntry);
        rebalanceAccess(p);

        return temp;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> p = treeSearch(tree.root(), key);

        if (tree.isExternal(p)) { // Key is not in the tree.
            rebalanceAccess(p);
            return null;
        }

        V temp = p.getElement().getValue();

        if (tree.isInternal(tree.right(p)) && tree.isInternal(tree.left(p))) {
            // Both children exist
            Position<Entry<K, V>> predecessor = treeMax(tree.left(p));

            tree.set(p, predecessor.getElement());
            p = predecessor;
        }

        Position<Entry<K, V>> leaf = tree.isExternal(tree.left(p)) ? tree.left(p) : tree.right(p);
        Position<Entry<K, V>> child = tree.sibling(leaf);

        tree.remove(leaf); // Simple since no children
        tree.remove(child); // Simple since 1 child

        rebalanceDelete(child);

        return temp;
    }

    /**
     * Returns the rightmost value of the subtree rooted at {@param root}.
     */
    protected Position<Entry<K, V>> treeMax(Position<Entry<K, V>> root) {
        if (isEmpty()) {
            return null;
        }

        Position<Entry<K, V>> walk = root;

        while (tree.isInternal(walk)) {
            walk = tree.right(walk);
        }

        return tree.parent(walk);
    }

    /**
     * Returns the leftmost value of the subtree rooted at {@param root}.
     */
    protected Position<Entry<K, V>> treeMin(Position<Entry<K, V>> root) {
        if (isEmpty()) {
            return null;
        }

        Position<Entry<K, V>> walk = root;

        while (tree.isInternal(root)) {
            walk = tree.left(walk);
        }

        return tree.parent(walk);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> entries = new ArrayList<>(size());

        for (Position<Entry<K, V>> p : tree.inorder()) {
            if (tree.isInternal(p)) { // Since leaf nodes are null
                entries.add(p.getElement());
            }
        }

        return entries;
    }

    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }

        return treeMin(tree.root()).getElement();
    }

    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }

        return treeMax(tree.root()).getElement();
    }

    // Not implemented
    // Should be the same as get() except you return the parent or max/min child if not found.
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

    /**
     * Returns an iterable of entries with keys in the range of [fromKey, toKey).
     */
    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();

        subMapRecurse(fromKey, toKey, tree.root(), buffer);

        return buffer;
    }

    /**
     * Basically an inorder traversal that is bounded by fromKey and toKey.
     *
     * If the root is smaller than fromKey, traverses the right subtree.
     * If the root is between fromKey and toKey, adds the root, then moves on to the right subtree.
     * If the root is larger than toKey, traverses the left subtree.
     */
    private void subMapRecurse(K fromKey, K toKey, Position<Entry<K, V>> root, ArrayList<Entry<K, V>> buffer) {
        if (tree.isExternal(root)) {
            return;
        }

        if (compare(root.getElement(), fromKey) < 0) {
            subMapRecurse(fromKey, toKey, tree.right(root), buffer);
        } else {
            subMapRecurse(fromKey, toKey, tree.left(root), buffer);

            if (compare(root.getElement(), toKey) < 0) {
                buffer.add(root.getElement());

                subMapRecurse(fromKey, toKey, tree.right(root), buffer);
            }
        }
    }

    // Stubs for balanced search tree operations (subclasses can override)
    /**
     * Rebalances the tree after an insertion of specified position.  This
     * version of the method does not do anything, but it can be
     * overridden by subclasses.
     * @param p the position which was recently inserted
     */
    protected void rebalanceInsert(Position<Entry<K,V>> p) { }

    /**
     * Rebalances the tree after a child of specified position has been
     * removed.  This version of the method does not do anything, but it
     * can be overridden by subclasses.
     * @param p the position of the sibling of the removed leaf
     */
    protected void rebalanceDelete(Position<Entry<K,V>> p) { }

    /**
     * Rebalances the tree after an access of specified position.  This
     * version of the method does not do anything, but it can be
     * overridden by a subclasses.
     * @param p the Position which was recently accessed (possibly a leaf)
     */
    protected void rebalanceAccess(Position<Entry<K,V>> p) { }

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
