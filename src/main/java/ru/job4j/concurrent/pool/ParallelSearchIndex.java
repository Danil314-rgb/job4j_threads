package ru.job4j.concurrent.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T value;

    public ParallelSearchIndex(T[] array, int from, int to, T value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return lineSort();
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex<T> leftSort = new ParallelSearchIndex(array, from, mid, value);
        ParallelSearchIndex<T> rightSort = new ParallelSearchIndex(array, mid + 1, to, value);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }

    public T search(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (T) forkJoinPool.invoke(new ParallelSearchIndex(array, 0, array.length - 1, value));

    }

    private int lineSort() {
        for (int i = from; i <= to; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
