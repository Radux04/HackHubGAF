package unicam.repository;

import unicam.model.utenti.staff.Staff;

import java.util.Optional;

public interface StaffRepository {

    Optional<Staff> findMentoreById(int id);

    void save(Staff staff);
    Staff findStaff(int id);
}
