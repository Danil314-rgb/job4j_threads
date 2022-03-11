package ru.job4j.concurrent.async;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SumColRolTest {

    @Test
    public void sum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] array = RolColSum.sum(matrix);
        assertThat(array[0].getRowSum(), is(6));
        assertThat(array[0].getColSum(), is(12));
    }

    @Test
    public void asyncSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] array = RolColSum.asyncSum(matrix);
        assertThat(array[0].getRowSum(), is(6));
        assertThat(array[0].getColSum(), is(12));
    }
}