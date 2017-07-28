package com.terrykwon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm such that if an element in an M x N matrix is 0, its entire row and column are set to 0.
 */
public class ZeroMatrix {

    /**
     * Keeps track of the rows and cols that should be zeroed using two sets.
     * Then iterates through the sets.
     *
     * Time complexity: O(N^2), specifically 3N^2
     * Space complexity: O(N), because at max num(rows) + num(cols) is stored.
     */
    private static void zeroMatrix(int[][] arr) {
        Set<Integer> targetRows = new HashSet<>();
        Set<Integer> targetCols = new HashSet<>();


        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                if (arr[r][c] == 0) {
                    targetRows.add(r);
                    targetCols.add(c);
                }
            }
        }

        for (int r : targetRows) {
            for (int c = 0; c < arr[0].length; c++) {
                arr[r][c] = 0;
            }
        }

        for (int c : targetCols) {
            for (int r = 0; r < arr.length; r++) {
                arr[r][c] = 0;
            }
        }
    }

    static int[][] generateIntMatrix(int width, int height) {
        int[][] matrix = new int[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                matrix[r][c] = 1;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] arr1 = generateIntMatrix(9, 7);
        arr1[1][2] = 0;
        arr1[5][1] = 0;
        Utils.printArray(arr1);

        zeroMatrix(arr1);
        Utils.printArray(arr1);
    }
}
