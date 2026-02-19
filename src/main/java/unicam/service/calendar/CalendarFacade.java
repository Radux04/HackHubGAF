package unicam.service.calendar;

import unicam.controller.calendar.GoogleCalendarController.CreateCallRequest;

public interface CalendarFacade {
    /**
     * Restituisce l'URL di autorizzazione Google da aprire nel browser.
     */
    String buildAuthUrl();

    /**
     * Gestisce il callback OAuth e salva i token necessari per usare il calendar.
     */
    void handleOAuthCallback(String code);

    /**
     * Crea una "call" (evento) nel calendario (per ora usiamo sempre 'primary').
     * Ritorna l'id dell'evento.
     */
    String creaCallCalendario(CreateCallRequest req, boolean withMeet);

    // Se in futuro vuoi legarlo a Hackathon/Team:
    // String creaCallPerIscrizione(Iscrizione iscrizione, CreateCallRequest req, boolean withMeet);
}
