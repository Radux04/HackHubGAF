package unicam.service;

import unicam.dto.cambiacordinatore.CambiaCoordinatoreDTO;
import unicam.dto.cambiateam.CambiaTeamDTO;
import unicam.dto.invito.InvitoDTO;
import unicam.model.inviti.Invito;
import unicam.repository.*;
import unicam.model.team.Team;
import unicam.model.team.builder.TeamBuilder;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

public class TeamService {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final InvitiRepository invitiRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository, InvitiRepository invitiRepository) {
        this.teamRepository = teamRepository;
        this.invitiRepository = invitiRepository;
        this.userRepository = userRepository;
    }
    //da mettere il DTO qui
    public Team creaTeam(String nome, String descrizione, Long idCoordinatore)
    {
        if(nome == null || nome.isBlank())
        {throw new IllegalArgumentException("errore nome team");}

        if (teamRepository.existsByNome(nome))
        {throw new IllegalArgumentException("errore nome team già esistente");}

        User c = userRepository.findById(idCoordinatore).get();

        if(c.getRuolo() != Ruoli.UTENTE){
            throw  new IllegalArgumentException("errore ruolo team");
        }

        c.setRuolo(Ruoli.COORDINATORE);
        Team team = new TeamBuilder()
                .buildNome(nome)
                .buildDescrizione(descrizione)
                .buildCoordinatore(idCoordinatore)
                .build();

        userRepository.save(c);

        return teamRepository.save(team);

    }

    public Invito invita(InvitoDTO invitoDTO) {
        User u = userRepository.findById(invitoDTO.getIdCoordinator()).get();

        if(u.getRuolo() != Ruoli.COORDINATORE) { throw new IllegalArgumentException("errore non sei coordinatore");}
        Invito invito = new Invito(u.getTeam(), u);
        invitiRepository.save(invito);
        return invito;
    }


    public boolean cambiaTeam(CambiaTeamDTO cambiaTeamDTO) {

        User user = userRepository.findById(cambiaTeamDTO.getIdMembroTeam()).get();
        teamRepository.findById(cambiaTeamDTO.getIdTeamAttuale()).get().getMembri().remove(user);
        user.setTeam(teamRepository.findById(cambiaTeamDTO.getIdNuovoTeam()).get());
        return teamRepository.findById(cambiaTeamDTO.getIdNuovoTeam()).get().getMembri().add(user);

    }

    public void cambiaCoordinatore(CambiaCoordinatoreDTO cambiaCoordinatoreDTO) {
        //momentaneamente questo metodo funge solo se un cordinatore decide di accettare l'invito di un altro team.
        userRepository.findById(idNuovoCoordinatore).setRuolo(Ruoli.COORDINATORE);
        teamRepository.getTeamById(idTeam).setCoordinatore(idNuovoCoordinatore);
    }


    public boolean removeMemberById(Long memberId, Long idCordinatore){
        //controllo se l'utente è un cordinatore
        User u = userRepository.findById(idCordinatore);
        if(u.getRuolo() != Ruoli.COORDINATORE) { return false; }
        //controllo a quale team il cordinatore che sta cercando di effettuare l'operazione appartiene
        Team t = teamRepository.findTeamByCoordinatoreId(idCordinatore);

        t.getMembri().remove(memberId);
        u = userRepository.findById(memberId);
        u.setIdTeam(-1);
        teamRepository.save(t);
        return true;
    }
}
