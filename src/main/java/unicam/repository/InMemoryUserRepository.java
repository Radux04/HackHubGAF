package unicam.repository;

import unicam.model.utenti.user.User;

import java.util.HashMap;
import java.util.Map;

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