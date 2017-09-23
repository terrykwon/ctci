package com.terrykwon.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * A BST was created by traversing through an array from left to right and inserting each element. Given a BST
 * with distinct elements, print all possible arrays that could have led to this tree.
 */
public class BSTSequences {

    private BSTSequences() {

    }

    /**
     * A solution using two recursive algorithms. Assuming that we have every viable sequence for the left
     * and right subtree of a node, weaving them together (and prepending the root's element) will yield
     * all viable sequences of the current node. This is repeated for all subtrees, recursing from the leaves.
     *
     * Time complexity: O(2^N)
     * Space complexity: O(2^N), b/c the number of permutations increase exponentially.
     */
    private static <E> ArrayList<LinkedList<E>> sequences(TreeNode<E> root) {
        if (root == null) {
            // Return an ArrayList with one empty LinkedList
            // The empty LinkedList is necessary b/c if the ArrayList is empty, the for loop below won't be called,
            // and a list containing just the prefix (root element) won't be returned.
            ArrayList<LinkedList<E>> empty = new ArrayList<>();
            empty.add(new LinkedList<>());
            return empty;
        }

        ArrayList<LinkedList<E>> results = new ArrayList<>();

        // Pass a "ready-made" prefix with the current root element to weaveLists().
        LinkedList<E> prefix = new LinkedList<>();
        prefix.add(root.element);

        // Assume we have every viable sequence for the left and right subtrees.
        ArrayList<LinkedList<E>> leftSeq = sequences(root.leftChild);
        ArrayList<LinkedList<E>> rightSeq = sequences(root.rightChild);

        // All possible weaves (permutations) of two viable sequences will be a valid sequence for the current root.
        for (LinkedList<E> left : leftSeq) {
            for (LinkedList<E> right : rightSeq) {
                ArrayList<LinkedList<E>> weaved = new ArrayList<>();
                weaveLists(left, right, prefix, weaved);
                results.addAll(weaved);
            }
        }

        return results;
    }

    /**
     * Generates all permutations of the combination of two lists <b>preserving relative order within each list.</b>
     *
     * With each recursion, removes an element from the head of a list and adds it to {@param prefix}.
     *
     * @param prefix temporarily stores one list while it's being "stacked" through recursion.
     * @param results a list of permutations.
     */
    private static <E> void weaveLists(LinkedList<E> l1, LinkedList<E> l2,
                                       LinkedList<E> prefix, ArrayList<LinkedList<E>> results) {

        // If one of the lists is empty, end the recursion by returning the prefix appended by all remaining elements.
        if (l1.isEmpty() || l2.isEmpty()) {
            LinkedList<E> res = (LinkedList<E>) prefix.clone(); // Shallow copy, not robust
            res.addAll(l1);
            res.addAll(l2);
            results.add(res);
            return;
        }

        E h1 = l1.removeFirst();
        prefix.addLast(h1);
        weaveLists(l1, l2, prefix, results);
        prefix.removeLast();
        l1.addFirst(h1);

        E h2 = l2.removeFirst();
        prefix.addLast(h2);
        weaveLists(l1, l2, prefix, results);
        prefix.removeLast();
        l2.addFirst(h2);
    }

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>(Arrays.asList(1, 2));
        LinkedList<Integer> l2 = new LinkedList<>(Arrays.asList(3, 4));
        ArrayList<LinkedList<Integer>> w1 = new ArrayList<>();

        weaveLists(l1, l2, new LinkedList<>(), w1);

//        printListOfLists(w1);

        TreeNode<Integer> t1 = buildTree();
        ArrayList<LinkedList<Integer>> r1 = sequences(t1);

        printListOfLists(r1);

    }

    private static TreeNode<Integer> buildTree() {
        TreeNode<Integer> root = new TreeNode<>(2);
        root.leftChild = new TreeNode<>(1);
        root.rightChild = new TreeNode<>(3);

        return root;
    }

    private static <E, L extends Iterable<E>> void printListOfLists(Iterable<L> list) {
        for (L l : list) {
            for (E i : l) {
                System.out.print(i);
            }
            System.out.println();
        }
    }


}
