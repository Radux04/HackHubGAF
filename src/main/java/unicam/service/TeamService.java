package unicam.service;

import org.springframework.stereotype.Service;
import unicam.dto.team.*;
import unicam.model.Invito;
import unicam.repository.*;
import unicam.model.Team;
import unicam.builder.TeamBuilder;
import unicam.model.Ruoli;
import unicam.model.User;

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
        if(creaTeamDTO.nome() == null || creaTeamDTO.nome().isBlank())
        {throw new IllegalArgumentException("errore nome team");}

        if (teamRepository.existsByNome(creaTeamDTO.nome()))
        {throw new IllegalArgumentException("errore nome team già esistente");}


        if(userRepository.findById(creaTeamDTO.idCordinatore()).get().getRuolo() != Ruoli.UTENTE){
            throw  new IllegalArgumentException("errore ruolo team");
        }

        userRepository.findById(creaTeamDTO.idCordinatore()).get().setRuolo(Ruoli.COORDINATORE);
        Team team = new TeamBuilder()
                .buildNome(creaTeamDTO.nome())
                .buildDescrizione(creaTeamDTO.descrizione())
                .buildCoordinatore(userRepository.findById(creaTeamDTO.idCordinatore()).get())
                .build();

        return teamRepository.save(team);

    }

    public Invito invita(InvitoDTO invitoDTO) {
        User u = userRepository.findById(invitoDTO.idCoordinator()).get();
        if(u.getRuolo() != Ruoli.COORDINATORE) { throw new IllegalArgumentException("errore non sei coordinatore");}
        User d = userRepository.findById(invitoDTO.idUser()).get();
        Invito invito = new Invito(u.getTeam(), d);
        invitiRepository.save(invito);
        return invito;
    }


    public boolean cambiaTeam(CambiaTeamDTO cambiaTeamDTO) {

        User user = userRepository.findById(cambiaTeamDTO.idMembroTeam()).get();
        teamRepository.findById(cambiaTeamDTO.idTeamAttuale()).get().getMembri().remove(user);
        user.setTeam(teamRepository.findById(cambiaTeamDTO.idNuovoTeam()).get());
        return teamRepository.findById(cambiaTeamDTO.idNuovoTeam()).get().getMembri().add(user);

    }

    public void cambiaCoordinatore(CambiaCoordinatoreDTO cambiaCoordinatoreDTO) {
        userRepository.findById(cambiaCoordinatoreDTO.idNuovoCoordinatore()).get().setRuolo(Ruoli.COORDINATORE);
        teamRepository.findById(cambiaCoordinatoreDTO.idTeam()).get().getCoordinatore().setRuolo(Ruoli.MEMBROTEAM);
        teamRepository.findById(cambiaCoordinatoreDTO.idTeam()).get().setCoordinatore(userRepository.findById(cambiaCoordinatoreDTO.idNuovoCoordinatore()).get());
    }


    public boolean removeMemberById(RemoveMemberDTO removeMemberDTO) {
        if(userRepository.findById(removeMemberDTO.coordinatoreId()).get().getRuolo() != Ruoli.COORDINATORE){
            return false;
        }
        teamRepository.findById(userRepository.findById(removeMemberDTO.memberId()).get().getTeam().getId()).get().getMembri().remove(userRepository.findById(removeMemberDTO.memberId()).get());
        userRepository.findById(removeMemberDTO.memberId()).get().setTeam(null);
        return true;
    }

    public boolean nuovoCoordinatore(Long membroId) {

        if(userRepository.findById(membroId).get().getTeam().getMembri().size() < 2){
            throw   new IllegalArgumentException("non ci sono altri membri nel team");
        }
        userRepository.findById(membroId).get().getTeam().getCoordinatore().setRuolo(Ruoli.MEMBROTEAM);
        userRepository.findById(membroId).get().getTeam().setCoordinatore(userRepository.findById(membroId).get());
        userRepository.findById(membroId).get().setRuolo(Ruoli.COORDINATORE);
        return true;
    }
}
