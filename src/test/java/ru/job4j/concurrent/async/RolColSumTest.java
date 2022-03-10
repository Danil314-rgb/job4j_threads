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
        RolColSum.Sums[] res = RolColSum.sum(arr);
        assertThat(res[1].getColSum(), is(15));
    }

    @Test
    public void whenAsyncSum() {
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] res = RolColSum.asyncSum(arr);
        assertThat(res[2].getRowSum(), is(24));
    }
}