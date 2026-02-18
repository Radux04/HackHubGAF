package unicam.dto.hackathon;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data
public class RichiestaSupportoDTO {
    Long idTeam;
    String descrizione;
}
