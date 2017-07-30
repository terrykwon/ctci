package com.terrykwon.arrays;

/**
 * Reverse an array in-place.
 */
public class ReverseArray {

    /**
     * Swaps the first and last character, and continues until the halfway point.
     *
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    private static void reverseArray(char[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static void main(String[] args) {
        String s = "hello this is a potato";
        char[] arr = s.toCharArray();

        reverseArray(arr);

        System.out.println(new String(arr));

    }
}
