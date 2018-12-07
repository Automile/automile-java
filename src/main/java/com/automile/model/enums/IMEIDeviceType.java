package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IMEIDeviceType {
    AutomileBox_750(0),
    AutomileBox_860(1),
    AutomileAnyTrack_1(2),
    AutomileMobile(3),
    TELTONIKA(4),
    UNKNOWN(255);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
