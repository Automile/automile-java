package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    STANDBY(0),
    OFFLINE(1),
    TRACKING(2),
    SLEEPING(3),
    SHUTDOWN_PENDING(4),
    SHUTDOWN(5);

    private Integer value;

    public String toString() { return String.valueOf(value); }
}
