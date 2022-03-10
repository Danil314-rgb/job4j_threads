package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(5);

    public void ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        tasks.poll().run();
                        Thread.currentThread().start();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }));

        }
    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        for (int i = 0; i < threads.size(); i++) {
            Thread.currentThread().interrupt();
        }
    }
}
