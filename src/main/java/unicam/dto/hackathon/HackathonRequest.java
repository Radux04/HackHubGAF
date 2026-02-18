package unicam.dto.hackathon;

import lombok.Data;

@Data
public class HackathonRequest {
    DescrizioneHT descrizione;
    PlacementHT placement;
    StaffHT staff;
    String nome;
    Long idOrganizzatore;
}
