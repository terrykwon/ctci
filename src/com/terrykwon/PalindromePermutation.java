package com.terrykwon;

import java.util.HashMap;

/**
 * Given a string, write a function to  check if it is a permutation of a palindrome.
 */
public class PalindromePermutation {

    /**
     * Count each character and check that each count is even, except possibly one.
     * Time complexity: O(N)
     * Space complexity: O(N)
     */
    private static boolean palindromePermutation(String s) {
        HashMap<Character, Integer> countMap = new HashMap<>();

        // This loop will count the occurrences of each character in the string.
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));

            if (c == ' ') {
                continue;
            }

            if (countMap.get(c) == null) {
                countMap.put(c, 1);
            } else {
                int count = countMap.get(c);
                countMap.put(c, count + 1);
            }
        }

        int oddCount = 0;

        for (Integer count : countMap.values()) {
            if (count % 2 != 0) {
                oddCount += 1;
            }

            if (oddCount > 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "Tact Coa";
        String s2 = "";
        String s3 = "atco atf";

        System.out.println(palindromePermutation(s1));
        System.out.println(palindromePermutation(s2));
        System.out.println(palindromePermutation(s3));

    }
}
