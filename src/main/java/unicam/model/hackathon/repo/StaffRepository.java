package unicam.model.hackathon.repo;

import unicam.model.utenti.staff.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffRepository {

    Optional<Staff> findOrganizzatoreById(int id);

    Optional<Staff> findGiudiceById(int id);

    Optional<Staff> findMentoreById(int id);

    List<Staff> findMentoriByIds(List<Integer> ids);

    void save(Staff staff);
}
