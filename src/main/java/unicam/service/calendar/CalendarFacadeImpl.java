package unicam.service.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicam.controller.calendar.GoogleCalendarController.CreateCallRequest;

@Service
@RequiredArgsConstructor
public class CalendarFacadeImpl implements CalendarFacade {
    private final GoogleCalendarService googleCalendarService;

    @Override
    public String buildAuthUrl() {
        return googleCalendarService.buildAuthUrl();
    }

    @Override
    public void handleOAuthCallback(String code) {
        googleCalendarService.exchangeCodeForTokens(code);
    }

    @Override
    public String creaCallCalendario(CreateCallRequest req, boolean withMeet) {
        // nascondiamo il fatto che usiamo sempre il calendario "primary"
        return googleCalendarService.insertCall("primary", req, withMeet);
    }
}
