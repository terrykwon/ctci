package com.terrykwon.arrays;

/**
 * Assume you have a method {@code isSubstring()} which checks if one word is a substring of another.
 * Given two strings, s1 and s2, write code to check if s2 is a rotation of s1
 * using only one call to {@code isSubstring()}.
 *
 * Note: replaced {@code isSubstring()} with {@link String#contains(CharSequence)}.
 */
public class StringRotation {

    /**
     * Multiplies one string by two.
     *
     * Time complexity: O(N)
     * Space complexity: O(N), specifically 2N
     */
    private static boolean isStringRotation(String s1, String s2) {

        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }

    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        String s3 = "bottlewater";
        String s4 = "waterwater";

        System.out.println(isStringRotation(s1, s2)); // true
        System.out.println(isStringRotation(s2, s2)); // true
        System.out.println(isStringRotation(s3, s1)); // true
        System.out.println(isStringRotation(s4, s2)); // false
    }
}
