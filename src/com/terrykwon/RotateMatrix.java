package com.terrykwon;

import static com.terrykwon.Utils.generateMatrix;
import static com.terrykwon.Utils.printArray;

/**
 * Rotate an N x N matrix by 90 degrees.
 * Can you do this in-place?
 */
public class RotateMatrix {

    /**
     * Creates an empty array then fills it according to an algorithm.
     *
     * Time complexity: O(N^2), where N is the length of one side.
     * Space complexity: O(N^2)
     */
    private static char[][] rotateMatrix(char[][] arr) {
        int l = arr.length;
        char[][] res = new char[l][l];

        for (int r = 0; r < l; r++) {
            for (int c = 0; c < l; c++) {
                res[c][l - r - 1] = arr[r][c];
            }
        }

        return res;
    }

    /**
     * Rotates an array in-place by traversing through layers.
     *
     * Time complexity: O(N^2), where N is the length of one side.
     * Space complexity: O(1)
     */
    private static void rotateMatrix2(char[][] arr) {
        for (int layer = 0; layer < arr.length / 2; layer++) {
            int lastIndex = arr.length - 1; // end decreases by 1

            // Traverse diagonally
            for (int col = layer; col < lastIndex - layer; col++) {
                int row = layer; // (0,0) -> (1,1) -> (2,2) ... both row and col increase by 1

                char top = arr[row][col];
                arr[row][col] = arr[lastIndex - col][row];
                arr[lastIndex - col][row] = arr[lastIndex - row][lastIndex - col];
                arr[lastIndex - row][lastIndex - col] = arr[col][lastIndex - row];
                arr[col][lastIndex - row] = top;

            }
        }
    }


    public static void main(String[] args) {
        char[][] arr1 = {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        char[][] arr2 = generateMatrix(5, 5);
        printArray(arr2);
        rotateMatrix2(arr2);
        printArray(arr2);

        char[][] arr3 = generateMatrix(2, 2);
        printArray(arr3);
        rotateMatrix2(arr3);
        printArray(arr3);
    }


}
