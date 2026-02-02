package unicam.model.team.service;

import unicam.model.team.Team;
import unicam.model.team.builder.TeamBuilder;
import unicam.model.team.repository.TeamRepository;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;

public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

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

        Team team = new TeamBuilder()
                .buildNome(nome)
                .buildDescrizione(descrizione)
                .buildCoordinatore(coordinatore)
                .build();

        coordinatore.setRuolo(Ruoli.COORDINATORE);

        return teamRepository.save(team);





    }

}
