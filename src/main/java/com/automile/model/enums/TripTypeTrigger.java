package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TripTypeTrigger {
    Start(0),
    End(1),
    DrivesBetween(2);

    private Integer value;

    public String toString() {
        return String.valueOf(value);
    }
}
