package ru.job4j.concurrent.cash;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    private Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.keySet().removeIf(key -> key == user.getId());
    }

    public synchronized boolean transfer(int fromId, int told, int amount) {
        boolean result = false;
        User userFirst = users.get(fromId);
        User userSecond = users.get(told);
        if (userFirst != null && userSecond != null && userFirst.getAmount() >= amount) {
            userFirst.setAmount(userFirst.getAmount() - amount);
            userSecond.setAmount(userSecond.getAmount() + amount);
            result = true;
        }
        return result;
    }
}
