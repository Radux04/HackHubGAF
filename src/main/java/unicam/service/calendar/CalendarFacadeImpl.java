package unicam.service.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.dto.CreateCallRequest;

@Service
@RequiredArgsConstructor
public class CalendarFacadeImpl{
    private final GoogleCalendarService googleCalendarService;

    public String buildAuthUrl() {
        return googleCalendarService.buildAuthUrl();
    }

    public void handleOAuthCallback(String code) {
        googleCalendarService.exchangeCodeForTokens(code);
    }

    public String creaCallCalendario(CreateCallRequest req, boolean withMeet) {
        return googleCalendarService.insertCall("primary", req, withMeet);
    }
}
