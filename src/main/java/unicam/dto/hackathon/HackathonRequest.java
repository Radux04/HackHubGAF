package unicam.dto.hackathon;


public record HackathonRequest (
    DescrizioneHT descrizione,
    PlacementHT placement,
    StaffHT staff,
    String nome,
    Long idOrganizzatore) {
}
