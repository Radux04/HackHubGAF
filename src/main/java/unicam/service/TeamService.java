package unicam.service;

import unicam.model.inviti.Invito;
import unicam.repository.InMemoryInvitiRepo;
import unicam.model.team.Team;
import unicam.model.team.builder.TeamBuilder;
import unicam.repository.InMemoryTeamRepository;
import unicam.model.utenti.user.Ruoli;
import unicam.model.utenti.user.User;
import unicam.repository.InMemoryUserRepository;
import unicam.repository.UserRepository;

public class TeamService {
    private final InMemoryTeamRepository teamRepository;
    private final InMemoryUserRepository userRepository;
    private final InMemoryInvitiRepo invitiRepository;

    public TeamService(InMemoryTeamRepository teamRepository, InMemoryUserRepository userRepository, InMemoryInvitiRepo invitiRepository) {
        this.teamRepository = teamRepository;
        this.invitiRepository = invitiRepository;
        this.userRepository = userRepository;
    }

    public Team creaTeam(String nome, String descrizione, int idCoordinatore)
    {
        if(nome == null || nome.isBlank())
        {throw new IllegalArgumentException("errore nome team");}


        if (teamRepository.existsByNome(nome))
        {throw new IllegalArgumentException("errore nome team già esistente");}


        User c = userRepository.findById(idCoordinatore);

        c.setRuolo(Ruoli.COORDINATORE);
        Team team = new TeamBuilder()
                .buildNome(nome)
                .buildDescrizione(descrizione)
                .buildCoordinatore(idCoordinatore)
                .build();

        userRepository.save(c);

        return teamRepository.save(team);

    }

    public Invito invita(int idUser, int idTeamMittente) {
        Invito invito = new Invito(idTeamMittente, idUser);
        invitiRepository.save(invito);
        return invito;
    }

    public boolean cambiaTeam(int idMembroTeam, int idTeamAttuale, int idNuovoTeam) {
        teamRepository.getTeamById(idTeamAttuale).getMembri().remove(idMembroTeam);
        userRepository.findById(idMembroTeam).setIdTeam(idNuovoTeam);
        return teamRepository.getTeamById(idNuovoTeam).getMembri().add(idMembroTeam);
    }

    public void cambiaCoordinatore(int idTeam, int idNuovoCoordinatore) {
        userRepository.findById(idNuovoCoordinatore).setRuolo(Ruoli.COORDINATORE);
        teamRepository.getTeamById(idTeam).setCoordinatore(idNuovoCoordinatore);
    }


    public boolean removeMemberById(int memberId, int idCordinatore){
        //controllo se l'utente è un cordinatore
        User u = userRepository.findById(idCordinatore);
        if(u.getRuolo() != Ruoli.COORDINATORE) { return false; }
        //controllo a quale team il cordinatore che sta cercando di effettuare l'operazione appartiene
        Team t = teamRepository.findTeamByCoordinatoreId(idCordinatore);

        t.getMembri().remove(memberId);
        u = userRepository.findById(memberId);
        u.setIdTeam(-1);
        teamRepository.save(t);
        return true;
    }
}
