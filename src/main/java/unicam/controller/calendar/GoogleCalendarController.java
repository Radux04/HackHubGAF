package unicam.controller.calendar;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.service.calendar.CalendarFacade;
import unicam.service.calendar.GoogleCalendarService;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/google")
@RequiredArgsConstructor
public class GoogleCalendarController {

    // In questo controller non vogliamo esporre direttamente il GoogleCalendarService, ma vogliamo usare una Facade.
//    private final GoogleCalendarService service;
//
//
//    // 1) Redirect a Google per autorizzare
//    @GetMapping("/auth")
//    public ResponseEntity<Void> auth() {
//        String url = service.buildAuthUrl();
//        return ResponseEntity.status(302).header("Location", url).build();
//    }
//
//    // 2) Callback: Google torna qui con ?code=...
//    @GetMapping("/callback")
//    public ResponseEntity<String> callback(@RequestParam String code) {
//        service.exchangeCodeForTokens(code);
//        return ResponseEntity.ok("OK. Token salvati. Ora puoi chiamare POST /google/calls");
//    }
//
//    // 3) Crea una call su Calendar (primary)
//    @PostMapping("/calls")
//    public ResponseEntity<String> createCall(@Valid @RequestBody CreateCallRequest req) {
//        String eventId = service.insertCall("primary", req, false);
//        return ResponseEntity.ok(eventId);
//    }
//
//    // DTO interno (così non crei altri file)
//    public record CreateCallRequest(
//            @NotBlank String title,
//            String description,
//            @NotNull OffsetDateTime start,
//            @NotNull OffsetDateTime end,
//            String attendeeEmail
//    ) {}

    private final CalendarFacade calendarFacade;

    // DTO interno, come avevamo fatto
    public record CreateCallRequest(
            String title,
            String description,
            OffsetDateTime start,
            OffsetDateTime end,
            String attendeeEmail
    ) {}

    // 1) Redirect manuale: restituisce l'URL Google da aprire
    @GetMapping("/auth")
    public ResponseEntity<Void> auth() {
        String url = calendarFacade.buildAuthUrl();
        return ResponseEntity.status(302)
                .header("Location", url)
                .build();
    }

    // 2) Callback di Google: code -> token
    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam String code) {
        calendarFacade.handleOAuthCallback(code);
        return ResponseEntity.ok("OK. Token salvati. Ora puoi chiamare POST /google/calls");
    }

    // 3) Creazione call nel calendar
    @PostMapping("/calls")
    public ResponseEntity<String> createCall(
            @RequestParam(defaultValue = "false") boolean meet,
            @Valid @RequestBody CreateCallRequest req
    ) {
        String eventId = calendarFacade.creaCallCalendario(req, meet);
        return ResponseEntity.ok(eventId);
    }
}
