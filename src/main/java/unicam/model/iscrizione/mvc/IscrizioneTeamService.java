package unicam.model.iscrizione.mvc;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.StatiHackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.builder.IscrizioneBuilder;
import unicam.model.iscrizione.repo.InMemoryIscrizioniRepository;
import unicam.model.team.Team;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.utenti.user.User;

import java.util.List;

public class IscrizioneTeamService {

    InMemoryTeamRepository inMemoryTeamRepository = new InMemoryTeamRepository();
    InMemoryIscrizioniRepository inMemoryIscrizioniRepository = new InMemoryIscrizioniRepository();




    public Team controlloTeam(int coordinatoreId){
        Team t = inMemoryTeamRepository.findTeamByCoordinatoreId(coordinatoreId);
        if(t.isOccupato()){
            throw new IllegalArgumentException();
        }
        else {
            return t;
        }
    }

    public Iscrizione iscriviTeam(List<User> l, Hackathon h, Team team){

        if(h.getStato() != StatiHackathon.IN_ISCRIZIONE){
            throw new IllegalArgumentException();
        }
        if(h.getDescrizione().getMaxSize() < l.size() || l.size() < 2){
            throw new IllegalArgumentException();
        }
        IscrizioneBuilder ib = new IscrizioneBuilder();
        ib.buildTeamId(team.getId());
        ib.buildPartecipanti(l);
        ib.buildHTId(h.getId());
        team.setOccupato(true);
        for(User u : l){
            u.setOccupato(true);
        }
        inMemoryTeamRepository.save(team);
        inMemoryIscrizioniRepository.save(ib.build());
        return ib.build();
    }
}
