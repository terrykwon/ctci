package com.terrykwon.arrays;

import java.util.Arrays;

/**
 * Write a method to replace all spaces in a string with '%20'.
 * You must modify the string in-place.
 */
public class Urlify {

    /**
     * Insert at end.
     *
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    private static String Urlify(String s) {
        if (s.length() == 0) {
            return s;
        }

        char[] arr = s.toCharArray();

        int l = arr.length - 1;
        int r = arr.length - 1;
        boolean isFiller = true;

        while (arr[r] == ' ') {
            if (isFiller && arr[l] != ' ') {
                isFiller = false;
            }

            if (!isFiller) {
                if (arr[l] == ' ') {
                    arr[r] = '0';
                    arr[r - 1] = '2';
                    arr[r - 2] = '%';
                    r -= 3;
                } else {
                    arr[r] = arr[l];
                    arr[l] = ' ';
                    r -= 1;
                }
            }

            l -= 1;
        }

        return new String(arr);
    }

    public static void main(String[] args) {
        String s1 = "Mr John Smith    ";
        String s2 = "";
        String s3 = "Johnny";
        String s4 = "I am your father      ";
        String s5 = "1";

        System.out.println(Urlify(s1));
        System.out.println(Urlify(s2));
        System.out.println(Urlify(s3));
        System.out.println(Urlify(s4));
        System.out.println(Urlify(s5));

    }
}
