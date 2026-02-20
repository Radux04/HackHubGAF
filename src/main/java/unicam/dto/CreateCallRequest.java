package unicam.dto;

public record CreateCallRequest(
        String title,
        String description,
        String start,
        String end,
        String attendeeEmail
) {}