package unicam.service;

import unicam.dto.team.*;
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
    public Team creaTeam(CreaTeamDTO creaTeamDTO)
    {
        if(creaTeamDTO.getNome() == null || creaTeamDTO.getNome().isBlank())
        {throw new IllegalArgumentException("errore nome team");}

        if (teamRepository.findByNome(creaTeamDTO.getNome()))
        {throw new IllegalArgumentException("errore nome team già esistente");}

        User c = userRepository.findById(creaTeamDTO.getIdCordinatore()).get();

        if(c.getRuolo() != Ruoli.UTENTE){
            throw  new IllegalArgumentException("errore ruolo team");
        }

        c.setRuolo(Ruoli.COORDINATORE);
        Team team = new TeamBuilder()
                .buildNome(creaTeamDTO.getNome())
                .buildDescrizione(creaTeamDTO.getDescrizione())
                .buildCoordinatore(creaTeamDTO.getIdCordinatore())
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
        userRepository.findById(cambiaCoordinatoreDTO.getIdNuovoCoordinatore()).get().setRuolo(Ruoli.COORDINATORE);
        teamRepository.findById(cambiaCoordinatoreDTO.getIdTeam()).get().setCoordinatore(userRepository.findById(cambiaCoordinatoreDTO.getIdNuovoCoordinatore()).get());
    }


    public boolean removeMemberById(RemoveMemberDTO removeMemberDTO) {
        //controllo se l'utente è un cordinatore
        User u = userRepository.findById(removeMemberDTO.getCoordinatoreId()).get();
        if(u.getRuolo() != Ruoli.COORDINATORE) { return false; }
        //controllo a quale team il cordinatore che sta cercando di effettuare l'operazione appartiene
        Team t = teamRepository.findByCoordinatore(u);

        t.getMembri().remove(removeMemberDTO.getMemberId());
        u = userRepository.findById(removeMemberDTO.getMemberId()).get();
        u.setId(null);
        teamRepository.save(t);
        return true;
    }
}
