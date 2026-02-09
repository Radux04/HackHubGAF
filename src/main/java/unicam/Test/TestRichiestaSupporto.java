package unicam.Test;

import unicam.model.hackathon.entity.Hackathon;
import unicam.model.hackathon.mvc.HackathonService;
import unicam.model.iscrizione.Iscrizione;
import unicam.model.iscrizione.repo.InMemoryIscrizioniRepository;
import unicam.model.supporto.RichiestaSupporto;
import unicam.model.team.Team;
import unicam.model.utenti.user.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestRichiestaSupporto {
    public static void main(String[] args) {
        int test = 1;

        HackathonService service = new HackathonService();

        Hackathon hackathon = new Hackathon();
        hackathon.setId(100);

        // Utenti
        User coord = new User("coord", "pwd", "c@mail.com");
        coord.setId(1);

        User member = new User("membro", "pwd", "m@mail.com");
        member.setId(2);

        User outsider = new User("estraneo", "pwd", "e@mail.com");
        outsider.setId(3);

        // Team
        List<User> membri = new ArrayList<>();
        membri.add(member);

        Team team = new Team("TeamA", "desc", coord, membri);
        team.setId(10);

        // Iscrizione (team -> hackathon)
        Iscrizione iscrizione = new Iscrizione();
        iscrizione.setId(1);
        iscrizione.setTeamId(team.getId());
        iscrizione.setHtId(hackathon.getId());
        salvaIscrizioneNelService(service, iscrizione);

        // Test 1: richiesta OK (coordinatore)
        try {
            boolean ok = service.richiediSupporto(team, coord, "Ho bisogno di supporto");
            printResult(test++, ok, "Richiesta supporto da coordinatore");
        } catch (Exception e) {
            printResult(test++, false, "Richiesta supporto da coordinatore (" + e.getMessage() + ")");
        }

        // Test 2: richiesta OK (membro)
        try {
            boolean ok = service.richiediSupporto(team, member, "Supporto tecnico");
            printResult(test++, ok, "Richiesta supporto da membro");
        } catch (Exception e) {
            printResult(test++, false, "Richiesta supporto da membro (" + e.getMessage() + ")");
        }

        // Test 3: utente non nel team
        try {
            service.richiediSupporto(team, outsider, "Non dovrei poterlo fare");
            printResult(test++, false, "Utente non nel team");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Utente non nel team (" + e.getMessage() + ")");
        }

        // Test 4: descrizione vuota
        try {
            service.richiediSupporto(team, coord, "  ");
            printResult(test++, false, "Descrizione vuota");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Descrizione vuota (" + e.getMessage() + ")");
        }

        // Test 5: team null
        try {
            service.richiediSupporto(null, coord, "x");
            printResult(test++, false, "Team null");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Team null (" + e.getMessage() + ")");
        }

        // Test 6: utente null
        try {
            service.richiediSupporto(team, null, "x");
            printResult(test++, false, "Utente null");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Utente null (" + e.getMessage() + ")");
        }

        // Test 7: team non iscritto a nessun hackathon
        try {
            Team teamNoIscr = new Team("TeamB", "desc", coord, membri);
            teamNoIscr.setId(20);
            service.richiediSupporto(teamNoIscr, coord, "non iscritto");
            printResult(test++, false, "Team non iscritto");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Team non iscritto (" + e.getMessage() + ")");
        }

        // Test 8: visualizza richieste supporto (2 richieste)
        try {
            List<RichiestaSupporto> lista = service.visualizzaRichiesteSupporto(hackathon);
            boolean ok = lista != null && lista.size() == 2;
            printResult(test++, ok, "Visualizza richieste supporto");
        } catch (Exception e) {
            printResult(test++, false, "Visualizza richieste supporto (" + e.getMessage() + ")");
        }

        // Test 9: hackathon null
        try {
            service.visualizzaRichiesteSupporto(null);
            printResult(test++, false, "Hackathon null");
        } catch (IllegalArgumentException e) {
            printResult(test++, true, "Hackathon null (" + e.getMessage() + ")");
        }

        // Test 10: hackathon senza richieste
        try {
            Hackathon hackathonVuoto = new Hackathon();
            hackathonVuoto.setId(999);
            List<RichiestaSupporto> lista = service.visualizzaRichiesteSupporto(hackathonVuoto);
            boolean ok = lista != null && lista.isEmpty();
            printResult(test++, ok, "Hackathon senza richieste");
        } catch (Exception e) {
            printResult(test++, false, "Hackathon senza richieste (" + e.getMessage() + ")");
        }
    }

    private static void salvaIscrizioneNelService(HackathonService service, Iscrizione iscrizione) {
        try {
            Field field = HackathonService.class.getDeclaredField("inMemoryIscrizioniRepository");
            field.setAccessible(true);
            InMemoryIscrizioniRepository repo = (InMemoryIscrizioniRepository) field.get(service);
            repo.save(iscrizione);
        } catch (Exception e) {
            throw new RuntimeException("Errore setup iscrizione: " + e.getMessage(), e);
        }
    }

    private static void printResult(int number, boolean ok, String label) {
        System.out.println("[" + number + "] " + (ok ? "OK" : "KO") + " - " + label);
    }
}