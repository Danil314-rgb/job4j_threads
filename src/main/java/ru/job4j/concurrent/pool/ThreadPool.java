package ru.job4j.concurrent.pool;

import ru.job4j.concurrent.wait.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private int size;
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public void ThreadPool() {
        this.size = Runtime.getRuntime().availableProcessors();

        for (int i = 0; i < size; i++) {
            /*создать потоки по количеству процессоров и поместить их в список потоков*/
            Thread t = new Thread();
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tasks.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
    }

    public void work(Runnable job) {
        new Thread(
                () -> {
                    try {
                        tasks.offer(job);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void shutdown() {
        for (int i = 0; i < threads.size(); i++) {
            Thread.currentThread().interrupt();
        }
    }
}
