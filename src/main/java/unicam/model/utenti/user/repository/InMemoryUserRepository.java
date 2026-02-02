package unicam.model.utenti.user.repository;

import unicam.model.utenti.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    private final Map<Integer, User> byId = new HashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public User findById(int id) {
        return byId.get(id);
    }

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(nextId.getAndIncrement());
        }
        byId.put(user.getId(), user);
        return user;
    }
}