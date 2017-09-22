package com.terrykwon.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A BST was created by traversing through an array from left to right and inserting each element. Given a BST
 * with distinct elements, print all possible arrays that could have led to this tree.
 */
public class BSTSequences {

    private BSTSequences() {

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

        printListOfLists(w1);

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
