package ru.job4j.concurrent.cash;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    private Map<Integer, User> users = new HashMap<>();

    public boolean add(User user) {
        boolean result = false;
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
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
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
            result = true;
        }
        return result;
    }

    public boolean transfer(int fromId, int told, int amount) {
        boolean result = false;
        if (users.containsKey(fromId) && users.containsKey(told) && users.get(fromId).getAmount() >= amount) {
            update(new User(fromId, users.get(fromId).getAmount() - amount));
            update(new User(told, users.get(told).getAmount() + amount));
            result = true;
        }
        return result;
    }
}
