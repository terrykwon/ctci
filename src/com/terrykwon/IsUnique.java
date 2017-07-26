package com.terrykwon;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 */
public class IsUnique {

    /**
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

    public static void main(String[] args) {
        String s = "123456";

        System.out.println(isUnique(s));
    }
}
