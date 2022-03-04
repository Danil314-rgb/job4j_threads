package ru.job4j.concurrent.async;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void whenSum() {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum sum = new RolColSum();
        assertThat(sum.sums(arr).getRowSum(), is(45));
    }

    @Test
    public void whenAsyncSum() throws Exception {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum sum = new RolColSum();
        assertThat(sum.asyncSum(arr).get().getColSum(), is(45));
    }
}