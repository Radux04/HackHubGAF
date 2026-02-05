package unicam.model.team.mvc;

import unicam.model.inviti.Invito;
import unicam.model.inviti.repo.InMemoryInvitiRepo;
import unicam.model.team.Team;
import unicam.model.team.builder.TeamBuilder;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;
import unicam.model.utenti.user.repository.InMemoryUserRepository;

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
}
