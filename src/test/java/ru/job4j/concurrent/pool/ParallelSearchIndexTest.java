package ru.job4j.concurrent.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParallelSearchIndexTest {

    @Test
    public void whenArrayIsFourItems() {
        String[] array = {"name", "age", "time", "work"};
        ParallelSearchIndex parallel = new ParallelSearchIndex(array, 0, array.length - 1, "age");
        assertThat(parallel.search(array, "age"), is(1));
    }

    @Test
    public void whenArrayIsTwelveItems() {
        Integer[] array = {1,2,3,4,5,6,7,8,9,10,11,12};
        ParallelSearchIndex parallel = new ParallelSearchIndex(array, 0, array.length - 1, 6);
        assertThat(parallel.search(array, 6), is(5));
    }
}