package unicam;

import unicam.model.inviti.repo.InMemoryInvitiRepo;
import unicam.model.team.Team;
import unicam.model.team.mvc.TeamController;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.team.mvc.TeamService;
import unicam.model.utenti.user.User;
import unicam.model.utenti.user.repository.InMemoryUserRepository;

public class TestCreaTeam {
    public static void main(String[] args) {
        // wiring
        InMemoryTeamRepository teamRepo = new InMemoryTeamRepository();
        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryInvitiRepo invRepo = new InMemoryInvitiRepo();

        TeamService teamService = new TeamService(teamRepo, userRepo, invRepo);
        TeamController teamController = new TeamController(teamService);

        // crea utente e salvalo
        User user = new User("mario", "pwd", "mario@mail.com");
        ((InMemoryUserRepository) userRepo).save(user);

        int test = 1;

        // Test 1: creazione team OK
        try {
            Team team1 = teamController.creaTeam("Alpha", "Primo team", user);
            boolean ok = team1 != null
                    && "Alpha".equals(team1.getNome())
                    && "Primo team".equals(team1.getDescrizione());
            printResult(test++, ok, "Creazione team");
        } catch (Exception e) {
            printResult(test++, false, "Creazione team (" + e.getMessage() + ")");
        }

        // Test 2: ruolo diventa COORDINATORE
        printResult(test++, "COORDINATORE".equals(user.getRuolo().name()),
                "Ruolo utente dopo creazione");

        // Test 3: errore se nome team già esistente
        try {
            teamController.creaTeam("Alpha", "Altro team", user);
            printResult(test++, false, "Nome team già esistente");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Nome team già esistente (" + e.getMessage() + ")");
        }
    }

    private static void printResult(int number, boolean ok, String label) {
        System.out.println("[" + number + "] " + (ok ? "OK" : "KO") + " - " + label);
    }
}
