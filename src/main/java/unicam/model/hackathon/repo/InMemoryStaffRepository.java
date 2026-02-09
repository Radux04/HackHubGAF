package unicam.model.hackathon.repo;

import unicam.model.utenti.staff.Staff;

import java.util.*;

public class InMemoryStaffRepository implements StaffRepository {
    private final Map<Integer, Staff> staffById = new HashMap<>();


    public Optional<Staff> findOrganizzatoreById(int id) {
        Staff s = staffById.get(id);
        if (s instanceof Staff o) {
            return Optional.of(o);
        }
        return Optional.empty();
    }


    public Optional<Staff> findGiudiceById(int id) {
        Staff s = staffById.get(id);
        if (s instanceof Staff g) {
            return Optional.of(g);
        }
        return Optional.empty();
    }


    public Optional<Staff> findMentoreById(int id) {
        Staff s = staffById.get(id);
        if (s instanceof Staff m) {
            return Optional.of(m);
        }
        return Optional.empty();
    }


    public List<Staff> findMentoriByIds(List<Integer> ids) {
        List<Staff> result = new ArrayList<>();
        for (int id : ids) {
            findMentoreById(id).ifPresent(result::add);
        }
        return result;
    }

    public void save(Staff staff) {
        staffById.put(staff.getId(), staff);
    }

}
