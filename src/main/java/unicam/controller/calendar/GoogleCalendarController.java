package unicam.controller.calendar;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicam.service.calendar.GoogleCalendarService;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/google")
public class GoogleCalendarController {

    private final GoogleCalendarService service;

    public GoogleCalendarController(GoogleCalendarService service) {
        this.service = service;
    }

    // 1) Redirect a Google per autorizzare
    @GetMapping("/auth")
    public ResponseEntity<Void> auth() {
        String url = service.buildAuthUrl();
        return ResponseEntity.status(302).header("Location", url).build();
    }

    // 2) Callback: Google torna qui con ?code=...
    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam String code) {
        service.exchangeCodeForTokens(code);
        return ResponseEntity.ok("OK. Token salvati. Ora puoi chiamare POST /google/calls");
    }

    // 3) Crea una call su Calendar (primary)
    @PostMapping("/calls")
    public ResponseEntity<String> createCall(@Valid @RequestBody CreateCallRequest req) {
        String eventId = service.insertCall("primary", req, false);
        return ResponseEntity.ok(eventId);
    }

    // DTO interno (così non crei altri file)
    public record CreateCallRequest(
            @NotBlank String title,
            String description,
            @NotNull OffsetDateTime start,
            @NotNull OffsetDateTime end,
            String attendeeEmail
    ) {}
}
