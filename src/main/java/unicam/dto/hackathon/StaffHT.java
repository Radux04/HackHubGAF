package unicam.dto.hackathon;

import java.util.List;

public record StaffHT (
    Long idGiudice,
    List<Long> mentori) {
}
