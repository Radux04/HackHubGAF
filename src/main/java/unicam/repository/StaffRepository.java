package unicam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicam.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

}
