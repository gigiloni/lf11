package de.bsz.assethub.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class LogEntry {

    private final LocalDateTime timestamp;
    private final String message;

    LogEntry(LocalDateTime timestamp, String message) {
        this.timestamp = Objects.requireNonNull(
                timestamp,
                "Timestamp must not be null"
        );
        this.message = Objects.requireNonNull(
                message,
                "Message must not be null"
        );
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return timestamp + ": " + message;
    }
}
