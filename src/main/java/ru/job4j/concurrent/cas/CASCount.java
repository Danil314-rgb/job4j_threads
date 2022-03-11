package ru.job4j.concurrent.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int ref = count.get();
        int temp = 0;
        count.compareAndSet(ref, temp);
    }

    public int get() {
        return count.get();
    }
}
