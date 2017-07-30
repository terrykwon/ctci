package com.terrykwon.arrays;

/**
 * Given two strings, write a function to check if they are one edit (or zero edits) away.
 * Edits: insert, delete, replace.
 */
public class OneAway {

    /**
     * Separate cases into checking whether the strings can be made the same
     * with (1) one shift, or (2) one replacement.
     *
     * Since insert and delete are relative (insert on s1 == delete on s2),
     * they can both be checked with {@link OneAway#checkShiftable(String, String)}.
     *
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    private static boolean oneAway(String s1, String s2) {
        int lengthDiff = s1.length() - s2.length();

        if (lengthDiff == 0) {
            return checkReplacable(s1, s2);
        } else if (lengthDiff == 1) { // s1 is longer by 1 character.
            return checkShiftable(s1, s2);
        } else if (lengthDiff == -1) {
            return checkShiftable(s2, s1);
        } else {
            return false;
        }

    }

    private static boolean checkShiftable(String s1, String s2) {
        // s1 is 1 character longer than s2.

        int diffCount = 0;
        int i1 = 0;
        int i2 = 0;

        while (i2 < s2.length()) {
            if (s1.charAt(i1) != s2.charAt(i2)) {
                diffCount++;
                // "Shift" the remaining characters in s1 (relative to s2).
                i1++;
            } else {
                i1++;
                i2++;
            }

            if (diffCount > 1) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkReplacable(String s1, String s2) {
        int diffCount = 0;

        // No more than 1 characters must differ.
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) {
                    return false;
                }
            }
        }

        return true;
    }


    public static void main(String[] args) {
        String s1 = "pale";
        String s2 = "ple";
        String s3 = "bale";
        String s4 = "pales";
        String s5 = "";
        String s6 = "pallid";
        String s7 = "pllid";

        System.out.println(oneAway(s1, s2)); // true
        System.out.println(oneAway(s1, s3)); // true
        System.out.println(oneAway(s1, s4)); // true
        System.out.println(oneAway(s5, s1)); // false
        System.out.println(oneAway(s3, s4)); // false
        System.out.println(oneAway(s6, s7)); // true
        System.out.println(oneAway(s7, s6)); // true


    }
}
