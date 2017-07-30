package com.terrykwon.arrays;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 */
public class IsUnique {

    /**
     * Simple double loop.
     *
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    private static boolean isUnique(String s) {
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Hash table implementation.
     *
     * Notes:
     * Chars can be used as array indices (automatically converted to int).
     *
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    private static boolean isUnique2(String s) {
        char[] arr = s.toCharArray();
        int[] table = new int[256]; // Assume there are only 256 possible characters (ASCII).

        for (char c : arr) {
            if (table[c] > 0) {
                return false;
            }

            table[c] += 1;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "1234566";

        System.out.println(isUnique2(s));

        char c = 'g';
        System.out.println(c + 'g');
    }
}
