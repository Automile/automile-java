package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HistoryType {
    MOVE_ALERT(1),
    TEMPERATURE(2),
    BATTERY_ALERT(4),
    HEARTBEAT(8),
    BATTERY_LEVEL(16),
    EXTERNAL_POWER_VOLTAGE(32),
    OPERATING_TIME(64),
    SHUTDOWN_PENDING(128),
    SHUTDOWN(256),
    OFFLINE(512);

    private Integer value;

    public String toString() { return String.valueOf(value); }
}
