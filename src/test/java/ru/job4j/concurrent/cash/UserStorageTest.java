package ru.job4j.concurrent.cash;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void add() {
        User first = new User(1, 500);
        UserStorage users = new UserStorage();
        assertTrue(users.add(first));
    }

    @Test
    public void delete() {
        User first = new User(1, 500);
        UserStorage users = new UserStorage();
        users.add(first);
        assertTrue(users.delete(first));
    }

    @Test
    public void update() {
        User first = new User(1, 500);
        User second = new User(1, 100);
        UserStorage users = new UserStorage();
        users.add(first);
        assertTrue(users.update(second));
    }

    @Test
    public void transfer() {
        User first = new User(1, 500);
        User second = new User(2, 100);
        UserStorage users = new UserStorage();
        users.add(first);
        users.add(second);
        assertFalse(users.transfer(2, 1, 200));
    }
}