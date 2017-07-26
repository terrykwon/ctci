package com.terrykwon;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {

    /**
     * Time complexity: O(N), since it traverses through 3 strings/maps.
     * Space complexity: O(N)
     *
     */
    private static boolean checkPermutation(String s1, String s2) {
        Map<Character, Integer> countMap = new HashMap<>();

        // Count the occurrences of each character and store it as a key-value pair.
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);

            if (countMap.get(c) == null) {
                countMap.put(c, 1);
            } else {
                Integer count = countMap.get(c);
                countMap.put(c, count + 1); // Overwrite pair with incremented count.
            }
        }

        // Subtract the count of each character from countMap.
        for (int j = 0; j < s2.length(); j++) {
            char c = s2.charAt(j);

            if (countMap.get(c) == null) {
                // If character did not appear in s1, return false.
                return false;
            }

            Integer count = countMap.get(c);
            countMap.put(c, count - 1);
        }

        // Make sure that each count is 0 (since s1 incremented and s2 decremented).
        for (Integer count : countMap.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "app";
        String s2 = "pppa";

        System.out.println(checkPermutation(s1, s2));
    }
}
