package ru.job4j.concurrent.dlist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    /*@GuardedBy("this")
    private final List<T> list;*/

    /*public SingleLockList(List<T> list) {
        this.list = (List) list.clone();
    }*/

    public synchronized void add(T value) {
    }

    public synchronized T get(int index) {
        return null;
    }

    public synchronized void copy() {

    }

    @Override
    public synchronized Iterator<T> iterator() {
        /*return copy(this.array).iterator();*/
        return null;
    }
}