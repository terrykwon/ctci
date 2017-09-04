package com.terrykwon.trees;


import com.terrykwon.Position;
import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    protected Node<E> root = null;
    private int size = 0;

    public LinkedBinaryTree() {

    }

    /**
     * A utility method to validate the position and return it as a node.
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid position type");
        }

        Node<E> node = (Node<E>) p;

        if (node.getParent() == node) { // Occurs when node is deleted
            throw new IllegalArgumentException("p is no longer in the tree.");
        }

        return node;
    }

    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return validate(p).getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return validate(p).getRight();
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
    }

    /**
     * Adds the root node.
     * @param e the element of the root.
     * @return  the added root node.
     * @throws IllegalStateException    if the tree is not empty.
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");

        root = createNode(e, null, null, null);
        size = 1;

        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);

        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }

        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;

        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);

        if (parent.getRight() != null) {
            throw new IllegalArgumentException("p already has a left child");
        }

        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;

        return child;
    }

    /**
     * Sets the value of the node at p.
     *
     * @return  the previous value at p.
     * @throws IllegalArgumentException if position p is invalid.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);

        return temp;
    }

    /**
     * Attaches existing trees as left and right subtrees at a leaf node.
     *
     * @param p the parent node on which trees are attached. This must be a leaf node.
     * @param left  the tree to attach as the left child.
     * @param right the tree to attach as the right child.
     * @throws IllegalArgumentException if the node at p is not a leaf node.
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> left, LinkedBinaryTree<E> right) throws IllegalArgumentException {
        Node<E> node = validate(p);

        if (isInternal(p)) {
            throw new IllegalArgumentException("p must be a leaf node.");
        }

        if (!left.isEmpty()) {
            left.root.setParent(node);
            node.setLeft(left.root);

            left.root = null;
            left.size = 0;
        }

        if (!right.isEmpty()) {
            right.root.setParent(node);
            node.setRight(right.root);

            right.root = null;
            right.size = 0;
        }

        size += left.size() + right.size();
    }

    /**
     * Removes the node at p replaces it with its child, if any.
     *
     * @param p the position of the node to remove.
     * @return  the removed element.
     * @throws IllegalArgumentException if the node at p has more than one children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);

        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p must have one or zero children.");
        }

        Node<E> child = node.getLeft() != null ? node.getLeft() : node.getRight();

        if (child != null) {
            child.setParent(node.getParent());
        }

        if (node == root) {
            root = child;
        } else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft()) {
                parent.setLeft(child);
            } else {
                parent.setRight(child);
            }
        }

        size--;

        E temp = node.getElement();

        // Help garbage collection
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);

        return temp;
    }

    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> iterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public E next() {
            return iterator.next().getElement();
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }

}
