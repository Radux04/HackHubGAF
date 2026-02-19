package unicam.dto.hackathon;

public record SegnalaTeamDTO(
        Long teamId,
        Long hackathonId,
        Long mentoreId,
        String descrizione) {
}
