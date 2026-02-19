package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.team.*;
import unicam.model.inviti.Invito;
import unicam.repository.*;
import unicam.model.team.Team;
import unicam.model.team.builder.TeamBuilder;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

import java.util.Optional;

@Service
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
                .buildCoordinatore(c)
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


    public boolean removeMemberById(Long membroId) {
        teamRepository.findById(userRepository.findById(membroId).get().getTeam().getId()).get().getMembri().remove(userRepository.findById(membroId).get());
        userRepository.findById(membroId).get().setId(null);
        return true;
    }

    public boolean nuovoCoordinatore(Long membroId) {
        Optional<User> u = userRepository.findById(membroId);
        if(userRepository.findById(membroId).get().getTeam().getMembri().size() < 2){
            throw   new IllegalArgumentException("non ci sono altri membri nel team");
        }
        userRepository.findById(membroId).get().setRuolo(Ruoli.MEMBROTEAM);
        userRepository.findById(membroId).get().getTeam().setCoordinatore(userRepository.findById(membroId).get());
        userRepository.findById(membroId).get().setRuolo(Ruoli.COORDINATORE);
        return true;
    }
}
