package com.terrykwon;

/**
 * Util methods for convenience.
 */
public class Utils {

    // Prevent instantiation
    private Utils() {

    }

    public static void printArray(char[][] arr) {
        for (char[] row : arr) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char[][] generateMatrix(int width, int height) {
        String seed = "abcdefghijklmnopqrstuvwxyz123456789";
        char[][] matrix = new char[width][height];

        for (int r = 0; r < width; r++) {
            for (int c = 0; c < height; c++) {
                matrix[r][c] = seed.charAt((width * r + c) % seed.length());
            }
        }

        return matrix;
    }


}
