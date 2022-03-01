package ru.job4j.concurrent.cash;

import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;

@ThreadSafe
public class UserStorage {

    private HashSet<User> users = new HashSet<>();

    public boolean add(User user) {
        boolean result = false;
        if (!users.contains(user)) {
            users.add(user);
            result = true;
        }
        return result;
    }

    public boolean update(User user) {
        boolean result = false;
        if (delete(user)) {
            add(user);
            result = true;
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        if (users.contains(user)) {
            users.remove(user);
            result = true;
        }
        return result;
    }

    public boolean transfer(int fromId, int told, int amount) {
       /*if (users.contains()) {

        }*/

        return false;
    }
}
