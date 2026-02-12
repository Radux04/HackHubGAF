package unicam.repository;

import unicam.model.utenti.staff.Staff;

import java.util.*;

public class InMemoryStaffRepository implements StaffRepository {
    private final Map<Integer, Staff> staffById = new HashMap<>();


    @Override
    public Optional<Staff> findMentoreById(int id) {
        Staff s = staffById.get(id);
        if (s instanceof Staff m) {
            return Optional.of(m);
        }
        return Optional.empty();
    }

    @Override
    public void save(Staff staff) {
        staffById.put(staff.getId(), staff);
    }

    @Override
    public Staff findStaff(int id) {
        return staffById.get(id);
    }

}
