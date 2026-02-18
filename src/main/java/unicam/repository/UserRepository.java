package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.utenti.user.User;

public interface UserRepository extends JpaRepository<User,Long> {
}