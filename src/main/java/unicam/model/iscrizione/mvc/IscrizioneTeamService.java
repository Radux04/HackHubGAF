package unicam.model.iscrizione.mvc;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.StatiHackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.builder.IscrizioneBuilder;
import unicam.model.iscrizione.repo.InMemoryIscrizioniRepository;
import unicam.model.iscrizione.repo.IscrizioneRepository;
import unicam.model.team.Team;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.team.repository.TeamRepository;
import unicam.model.utenti.user.User;

import java.util.ArrayList;
import java.util.List;

public class IscrizioneTeamService {

    private final TeamRepository inMemoryTeamRepository;
    private final IscrizioneRepository inMemoryIscrizioniRepository;

    public IscrizioneTeamService(InMemoryTeamRepository inMemoryTeamRepository, InMemoryIscrizioniRepository inMemoryIscrizioniRepository) {
        this.inMemoryTeamRepository = inMemoryTeamRepository;
        this.inMemoryIscrizioniRepository = inMemoryIscrizioniRepository;
    }

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

    public List<User> caricaMembriTeam(Team team) {
        List<User> membriTeam = new ArrayList<>();
        for(User m : team.getMembri()){
            membriTeam.add(m);
        }
        return membriTeam;
    }

    public List<User> selezionePartecipanti(List<User> l) {
        if(l.size() < 2){
            throw new IllegalArgumentException();
        }
        return l;
    }
}
