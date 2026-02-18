package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unicam.model.utenti.staff.Staff;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {

}
