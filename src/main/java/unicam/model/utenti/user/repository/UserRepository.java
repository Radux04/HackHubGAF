package unicam.model.utenti.user.repository;

import unicam.model.utenti.user.User;

public interface UserRepository {
    User findById(int id);
}