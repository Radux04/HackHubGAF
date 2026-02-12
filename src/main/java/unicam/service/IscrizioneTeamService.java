package unicam.service;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.StatiHackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.builder.IscrizioneBuilder;
import unicam.repository.*;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.util.ArrayList;
import java.util.List;

public class IscrizioneTeamService {

    private final TeamRepository inMemoryTeamRepository;
    private final IscrizioneRepository inMemoryIscrizioniRepository;
    private final HackathonRepository inMemoryHackathonRepository;

    public IscrizioneTeamService(InMemoryTeamRepository inMemoryTeamRepository, InMemoryIscrizioniRepository inMemoryIscrizioniRepository, InMemoryHackathonRepository inMemoryHackathonRepository) {
        this.inMemoryTeamRepository = inMemoryTeamRepository;
        this.inMemoryIscrizioniRepository = inMemoryIscrizioniRepository;
        this.inMemoryHackathonRepository = inMemoryHackathonRepository;
    }

    public int controlloTeam(int coordinatoreId){
        Team t = inMemoryTeamRepository.findTeamByCoordinatoreId(coordinatoreId);
        if(t.isOccupato()){
            throw new IllegalArgumentException();
        }
        else {
            return t.getId();
        }
    }

    public Iscrizione iscriviTeam(List<Integer> l, int idHackathon, int idTeam){
        Hackathon hackathon = inMemoryHackathonRepository.getHackathonById(idHackathon);

        Team team = inMemoryTeamRepository.findTeamById(idTeam);

        if(hackathon.getStato() != StatiHackathon.IN_ISCRIZIONE){
            throw new IllegalArgumentException();
        }
        if(hackathon.getDescrizione().getMaxSize() < l.size() || l.size() < 2){
            throw new IllegalArgumentException();
        }
        IscrizioneBuilder ib = new IscrizioneBuilder();
        ib.buildTeamId(idTeam);
        ib.buildPartecipanti(l);
        ib.buildHTId(idHackathon);
        team.setOccupato(true);
        for(User u : team.getMembri()){
            u.setOccupato(true);
        }
        inMemoryTeamRepository.save(team);
        inMemoryIscrizioniRepository.save(ib.build());
        return ib.build();
    }

    public List<Integer> caricaMembriTeam(int idTeam) {
        List<Integer> membriTeamId = new ArrayList<>();
        for(int m : inMemoryTeamRepository.getTeamById(idTeam).getMembri()){
            membriTeamId.add(m);
        }
        return membriTeamId;
    }

    public List<Integer> selezionePartecipanti(List<Integer> l) {
        if(l.size() < 2){
            throw new IllegalArgumentException();
        }
        return l;
    }
}
