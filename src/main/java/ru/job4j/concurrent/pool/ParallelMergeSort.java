package ru.job4j.concurrent.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<Integer> {

    private final int[] array;
    private final int from;
    private final int to;
    private final int value;

    public ParallelMergeSort(int[] array, int from, int to, int value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
            return lineSort();
        }
        int mid = (from + to) / 2;
        ParallelMergeSort leftSort = new ParallelMergeSort(array, from, mid, 2);
        ParallelMergeSort rightSort = new ParallelMergeSort(array, mid + 1, to, 2);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        if (left == value) {
            return left;
        } else if (right == value) {
            return right;
        } else {
            return -1;
        }
    }

    private int lineSort() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /*public static void main(String[] args) {
        int[] array = {1,3,5,6,2,3,1,7,8,9,0,1,3,4};
        ParallelMergeSort par = new ParallelMergeSort(array, 0, array.length - 1, 2);
        int res = par.compute();
        System.out.println(res);
    }*/
}
