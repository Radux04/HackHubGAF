package unicam.model.hackathon.repo;

import unicam.model.utenti.staff.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {

    Optional<Staff> findMentoreById(int id);

    void save(Staff staff);
}
