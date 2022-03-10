package ru.job4j.concurrent.async;

import java.util.concurrent.CompletableFuture;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int sum = 0;
        Sums[] result = column(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            Sums sums = result[i];
            sums.setRowSum(sum);
            result[i] = sums;
            sum = 0;
        }
        return result;
    }

    private static Sums[] column(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            int c = 0;
            int sum = 0;
            while (c < matrix.length) {
                sum += matrix[c++][i];
            }
            Sums sums = new Sums();
            sums.setColSum(sum);
            result[i] = sums;
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        CompletableFuture<Sums[]> result = CompletableFuture.completedFuture(sum(matrix));
        return result.join();
    }
}
