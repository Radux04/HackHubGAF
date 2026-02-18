package unicam.dto.hackathon;

import lombok.Data;

import java.util.List;

@Data
public class StaffHT {
    private Long idGiudice;
    private List<Long> mentori;
}
