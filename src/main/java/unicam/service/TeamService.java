package unicam.service;

import unicam.model.inviti.Invito;
import unicam.repository.InMemoryInvitiRepo;
import unicam.model.team.Team;
import unicam.model.team.builder.TeamBuilder;
import unicam.repository.InMemoryTeamRepository;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;
import unicam.repository.InMemoryUserRepository;

public class TeamService {
    private final InMemoryTeamRepository teamRepository;
    private final InMemoryUserRepository userRepository;
    private final InMemoryInvitiRepo invitiRepository;

    public TeamService(InMemoryTeamRepository teamRepository, InMemoryUserRepository userRepository, InMemoryInvitiRepo invitiRepository) {
        this.teamRepository = teamRepository;
        this.invitiRepository = invitiRepository;
        this.userRepository = userRepository;
    }

    public Team creaTeam(String nome, String descrizione, User coordinatore)
    {
        if(nome == null || nome.isBlank())
        {throw new IllegalArgumentException("errore nome team");}

        if(descrizione == null || descrizione.isBlank())
        {throw new IllegalArgumentException("errore descrizione team");}

        if (teamRepository.existsByNome(nome))
        {throw new IllegalArgumentException("errore nome team già esistente");}

        if(coordinatore == null)
        {throw new IllegalArgumentException("errore coordinatore team");}

        coordinatore.setRuolo(Ruoli.COORDINATORE);
        Team team = new TeamBuilder()
                .buildNome(nome)
                .buildDescrizione(descrizione)
                .buildCoordinatore(coordinatore)
                .build();

        userRepository.save(coordinatore);

        return teamRepository.save(team);

    }

    public Invito invita(User user, Team mittente) {
        Invito invito = new Invito(mittente.getId(), user.getId());
        invitiRepository.save(invito);
        return invito;
    }

    public boolean cambiaTeam(User membroTeam, Team teamAttuale, Team nuovoTeam) {
        teamAttuale.getMembri().remove(membroTeam);
        membroTeam.setTeam(nuovoTeam);
        return nuovoTeam.getMembri().add(membroTeam);
    }

    public void cambiaCoordinatore(Team team, User nuovoCoordinatore) {
        nuovoCoordinatore.setRuolo(Ruoli.COORDINATORE);
        team.setCoordinatore(nuovoCoordinatore);
    }
}
