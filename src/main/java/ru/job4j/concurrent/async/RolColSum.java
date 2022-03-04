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

    public Sums sums(int[][] matrix) {
        int sumC = 0;
        int sumR = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sumC += matrix[j][i];
                sumR += matrix[i][j];
            }
        }
        Sums sums = new Sums();
        sums.setColSum(sumC);
        sums.setRowSum(sumR);
        return sums;
    }

    public CompletableFuture<Sums> asyncSum(int[][] matrix) {
        return CompletableFuture.supplyAsync(
                () -> {
                    return sums(matrix);
                }
        );
    }
}
