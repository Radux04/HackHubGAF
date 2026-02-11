package unicam.repository;

import unicam.model.utenti.user.User;

public interface UserRepository {
    User findById(int id);
}