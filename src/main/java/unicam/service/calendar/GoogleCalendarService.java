package unicam.service.calendar;

import io.github.cdimascio.dotenv.Dotenv;
import net.fortuna.ical4j.model.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import unicam.controller.calendar.GoogleCalendarController.CreateCallRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;


@Service
public class GoogleCalendarService {

    private final WebClient webClient = WebClient.builder().build();

    Dotenv dotenv = Dotenv.load();

    String clientId = dotenv.get("GOOGLE_CLIENT_ID");
    String clientSecret = dotenv.get("GOOGLE_CLIENT_SECRET");
    String redirectUri = dotenv.get("GOOGLE_REDIRECT_URI");
    String scope = Optional.ofNullable(dotenv.get("GOOGLE_SCOPE"))
            .orElse("https://www.googleapis.com/auth/calendar.events");

    public GoogleCalendarService() {
        if (clientId == null || clientId.isBlank()) throw new IllegalStateException("Manca GOOGLE_CLIENT_ID nel .env");
        if (clientSecret == null || clientSecret.isBlank()) throw new IllegalStateException("Manca GOOGLE_CLIENT_SECRET nel .env");
        if (redirectUri == null || redirectUri.isBlank()) throw new IllegalStateException("Manca GOOGLE_REDIRECT_URI nel .env");
    }


    // DEMO storage (in memoria)
    private volatile String accessToken;
    private volatile String refreshToken;
    private volatile Instant accessTokenExpiry;

    public String buildAuthUrl() {
        // state: anti-CSRF (per demo possiamo lasciarlo semplice)
        String state = UUID.randomUUID().toString();

        return "https://accounts.google.com/o/oauth2/v2/auth"
                + "?response_type=code"
                + "&client_id=" + urlEnc(clientId)
                + "&redirect_uri=" + urlEnc(redirectUri)
                + "&scope=" + urlEnc(scope)
                + "&access_type=offline"
                + "&prompt=consent"
                + "&state=" + urlEnc(state);
    }

    public void exchangeCodeForTokens(String code) {
        // POST token endpoint
        Map<String, String> form = new LinkedHashMap<>();
        form.put("code", code);
        form.put("client_id", clientId);
        form.put("client_secret", clientSecret);
        form.put("redirect_uri", redirectUri);
        form.put("grant_type", "authorization_code");

        Map<?, ?> resp = webClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(toFormBody(form))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (resp == null || resp.get("access_token") == null) {
            throw new IllegalStateException("Token exchange fallito: " + resp);
        }

        this.accessToken = resp.get("access_token").toString();

        // refresh_token arriva di solito solo la prima volta o se prompt=consent
        Object rt = resp.get("refresh_token");
        if (rt != null) this.refreshToken = rt.toString();

        Object expiresObj = resp.containsKey("expires_in") ? resp.get("expires_in") : 3600;

        long expiresIn = (expiresObj instanceof Number)
                ? ((Number) expiresObj).longValue()
                : Long.parseLong(expiresObj.toString());
        this.accessTokenExpiry = Instant.now().plusSeconds(expiresIn - 30); // margine
    }

//    public String insertCall(String calendarId, CreateCallRequest req, boolean withMeet) {
//        ensureValidAccessToken();
//
//        // Convertiamo start/end in UTC (Z) per evitare casini di formato
//        String startUtc = req.start();
//        String endUtc = req.end();
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("summary", req.title());
//        if (req.description() != null) {
//            body.put("description", req.description());
//        }
//
//        // start / end come consiglia Google: dateTime + timeZone
//        Map<String, Object> start = new LinkedHashMap<>();
//        start.put("dateTime", startUtc.toString());     // es: 2026-02-20T14:00:00Z
//        start.put("timeZone", "Europe/Rome");
//
//        Map<String, Object> end = new LinkedHashMap<>();
//        end.put("dateTime", endUtc.toString());
//        end.put("timeZone", "Europe/Rome");
//
//        body.put("start", start);
//        body.put("end", end);
//
//        // PER ORA: niente attendees, niente Meet, per togliere variabili
//        // Se vuoi riaggiungere l’attendee dopo che funziona:
//        // if (req.attendeeEmail() != null && !req.attendeeEmail().isBlank()) {
//        //     body.put("attendees", List.of(Map.of("email", req.attendeeEmail())));
//        // }
//
//        String uri = "https://www.googleapis.com/calendar/v3/calendars/{calId}/events";
//
//        // PER ORA ignoriamo withMeet, vogliamo solo far funzionare l'evento base
//        // Se dopo funziona, aggiungiamo conferenceDataVersion ecc.
//
//        try {
//            System.out.println("=== REQUEST BODY VERSO GOOGLE ===");
//            System.out.println(body);
//            System.out.println("=================================");
//
//            Map<?, ?> resp = webClient.post()
//                    .uri(uri, calendarId)
//                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .bodyValue(body)
//                    .retrieve()
//                    .bodyToMono(Map.class)
//                    .block();
//
//            System.out.println("=== RESPONSE GOOGLE ===");
//            System.out.println(resp);
//            System.out.println("=======================");
//
//            if (resp == null || resp.get("id") == null) {
//                throw new IllegalStateException("Creazione evento fallita: " + resp);
//            }
//
//            return resp.get("id").toString();
//
//        } catch (WebClientResponseException ex) {
//            System.err.println("=== ERRORE GOOGLE CALENDAR ===");
//            System.err.println("Status: " + ex.getStatusCode());
//            System.err.println("Body: " + ex.getResponseBodyAsString());
//            System.err.println("================================");
//            throw new RuntimeException("Errore Google Calendar: " + ex.getStatusCode(), ex);
//        }
//    }

    public String insertCall(String calendarId, CreateCallRequest req, boolean withMeet) {
        ensureValidAccessToken();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("summary", req.title());

        if (req.description() != null) {
            body.put("description", req.description());
        }

        // start / end
        Map<String, Object> start = new LinkedHashMap<>();
        start.put("dateTime", req.start());   // ora è String
        start.put("timeZone", "Europe/Rome");

        Map<String, Object> end = new LinkedHashMap<>();
        end.put("dateTime", req.end());
        end.put("timeZone", "Europe/Rome");

        body.put("start", start);
        body.put("end", end);

        if (req.attendeeEmail() != null && !req.attendeeEmail().isBlank()) {
            body.put("attendees", List.of(Map.of("email", req.attendeeEmail())));
        }

        String uri = "https://www.googleapis.com/calendar/v3/calendars/{calId}/events";

        // 🔥 AGGIUNTA MEET
        if (withMeet) {
            body.put("conferenceData", Map.of(
                    "createRequest", Map.of(
                            "requestId", UUID.randomUUID().toString(),
                            "conferenceSolutionKey", Map.of("type", "hangoutsMeet")
                    )
            ));

            uri += "?conferenceDataVersion=1";
        }

        try {
            Map<?, ?> resp = webClient.post()
                    .uri(uri, calendarId)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (resp == null || resp.get("id") == null) {
                throw new IllegalStateException("Creazione evento fallita: " + resp);
            }

            // 🔥 Recuperiamo il link Meet se esiste
            if (withMeet && resp.get("conferenceData") != null) {
                Map<?, ?> conf = (Map<?, ?>) resp.get("conferenceData");
                if (conf.get("entryPoints") != null) {
                    List<?> entryPoints = (List<?>) conf.get("entryPoints");
                    for (Object ep : entryPoints) {
                        Map<?, ?> entry = (Map<?, ?>) ep;
                        if ("video".equals(entry.get("entryPointType"))) {
                            System.out.println("MEET LINK: " + entry.get("uri"));
                        }
                    }
                }
            }

            return resp.get("id").toString();

        } catch (WebClientResponseException ex) {
            System.err.println("=== ERRORE GOOGLE CALENDAR ===");
            System.err.println("Status: " + ex.getStatusCode());
            System.err.println("Body: " + ex.getResponseBodyAsString());
            System.err.println("================================");
            throw new RuntimeException("Errore Google Calendar: " + ex.getStatusCode(), ex);
        }
    }


    private void ensureValidAccessToken() {
        if (accessToken == null) {
            throw new IllegalStateException("Devi prima autorizzare: apri GET /google/auth e rifai il giro.");
        }
        if (accessTokenExpiry != null && Instant.now().isAfter(accessTokenExpiry)) {
            refreshAccessToken();
        }
    }

    private void refreshAccessToken() {
        if (refreshToken == null) {
            throw new IllegalStateException("Access token scaduto e refresh_token mancante. Rifai GET /google/auth");
        }

        Map<String, String> form = new LinkedHashMap<>();
        form.put("client_id", clientId);
        form.put("client_secret", clientSecret);
        form.put("refresh_token", refreshToken);
        form.put("grant_type", "refresh_token");

        Map<?, ?> resp = webClient.post()
                .uri("https://oauth2.googleapis.com/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(toFormBody(form))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (resp == null || resp.get("access_token") == null) {
            throw new IllegalStateException("Refresh token fallito: " + resp);
        }

        this.accessToken = resp.get("access_token").toString();
        Object expiresObj = resp.get("expires_in");
        long expiresIn = expiresObj == null ? 3600L : ((Number) expiresObj).longValue();

        this.accessTokenExpiry = Instant.now().plusSeconds(expiresIn - 30);
    }

    // helpers
    private static String urlEnc(String s) {
        return java.net.URLEncoder.encode(s, java.nio.charset.StandardCharsets.UTF_8);
    }

    private static String toFormBody(Map<String, String> form) {
        StringBuilder sb = new StringBuilder();
        for (var e : form.entrySet()) {
            if (sb.length() > 0) sb.append("&");
            sb.append(urlEnc(e.getKey())).append("=").append(urlEnc(e.getValue()));
        }
        return sb.toString();
    }
}

