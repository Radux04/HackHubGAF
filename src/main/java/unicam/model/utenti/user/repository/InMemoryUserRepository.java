package unicam.model.utenti.user.repository;

import unicam.model.utenti.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserRepository implements UserRepository {
    private Map<Integer, User> byId = new HashMap<>();

    @Override
    public User findById(int id) {
        return byId.get(id);
    }

    public User save(User user) {
        user.setId(byId.size());
        byId.put(user.getId(), user);
        return user;
    }
}