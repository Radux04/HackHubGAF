package unicam;

import unicam.model.team.Team;
import unicam.model.team.controller.TeamController;
import unicam.model.team.repository.InMemoryTeamRepository;
import unicam.model.team.repository.TeamRepository;
import unicam.model.team.service.TeamService;
import unicam.model.utenti.user.User;
import unicam.model.utenti.user.repository.InMemoryUserRepository;
import unicam.model.utenti.user.repository.UserRepository;

public class TestCreaTeam {
    public static void main(String[] args) {
        // wiring
        UserRepository userRepo = new InMemoryUserRepository();
        TeamRepository teamRepo = new InMemoryTeamRepository();
        TeamService teamService = new TeamService(teamRepo);
        TeamController teamController = new TeamController(teamService, userRepo);

        // crea utente e salvalo
        User user = new User("mario", "pwd", "mario@mail.com");
        ((InMemoryUserRepository) userRepo).save(user);

        int test = 1;

        // Test 1: creazione team OK
        try {
            Team team1 = teamController.creaTeam("Alpha", "Primo team", user.getId());
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
            teamController.creaTeam("Alpha", "Altro team", user.getId());
            printResult(test++, false, "Nome team già esistente");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Nome team già esistente (" + e.getMessage() + ")");
        }
    }

    private static void printResult(int number, boolean ok, String label) {
        System.out.println("[" + number + "] " + (ok ? "OK" : "KO") + " - " + label);
    }
}
