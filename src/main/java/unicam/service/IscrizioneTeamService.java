package unicam.service;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.entity.StatiHackathon;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.builder.IscrizioneBuilder;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;
import unicam.repository.*;
import unicam.model.team.Team;
import java.util.List;

public class IscrizioneTeamService {

    private final TeamRepository inMemoryTeamRepository;
    private final IscrizioneRepository inMemoryIscrizioniRepository;
    private final HackathonRepository inMemoryHackathonRepository;
    private final UserRepository inMemoryUserRepository;

    public IscrizioneTeamService(InMemoryTeamRepository inMemoryTeamRepository, InMemoryIscrizioniRepository inMemoryIscrizioniRepository, InMemoryHackathonRepository inMemoryHackathonRepository,  InMemoryUserRepository inMemoryUserRepository) {
        this.inMemoryTeamRepository = inMemoryTeamRepository;
        this.inMemoryIscrizioniRepository = inMemoryIscrizioniRepository;
        this.inMemoryHackathonRepository = inMemoryHackathonRepository;
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    //controlla se un team è impegnato in un hackaton restituendo l'id del team in questione
    public int controlloTeam(int coordinatoreId){
        Team t = inMemoryTeamRepository.findTeamByCoordinatoreId(coordinatoreId);
        if(t.isOccupato()){
            throw new IllegalArgumentException();
        }
        else {
            return t.getId();
        }
    }

    public Iscrizione iscriviTeam(int coordinatoreId, int idHackathon, int idTeam){

        Hackathon hackathon = inMemoryHackathonRepository.getHackathonById(idHackathon);

        if(hackathon.getStato() != StatiHackathon.IN_ISCRIZIONE){
            throw new IllegalArgumentException();
        }

        Team team = inMemoryTeamRepository.findTeamById(idTeam);


        if(hackathon.getDescrizione().getMaxSize() < team.getMembri().size()){
            throw new IllegalArgumentException();
        }
        User us = inMemoryUserRepository.findById(coordinatoreId);

        if(us.getRuolo()!= Ruoli.COORDINATORE) { throw new IllegalArgumentException("Non puoi fare questa azione, non sei Coordinatore"); }

        IscrizioneBuilder ib = new IscrizioneBuilder();
        ib.buildTeamId(idTeam);
        ib.buildPartecipanti(team.getMembri());
        ib.buildHTId(idHackathon);
        team.setOccupato(true);
        for(int u : team.getMembri()){
            inMemoryUserRepository.findById(u).setOccupato(true);
        }
        inMemoryTeamRepository.save(team);
        inMemoryIscrizioniRepository.save(ib.build());
        return ib.build();
    }

    public List<Integer> caricaMembriTeam(int idTeam) {
        return inMemoryTeamRepository.getTeamById(idTeam).getMembri();
    }

    public List<Integer> selezionePartecipanti(List<Integer> l) {
        if(l.size() < 2){
            throw new IllegalArgumentException();
        }
        return l;
    }
}
