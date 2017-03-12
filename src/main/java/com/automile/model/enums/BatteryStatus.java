package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BatteryStatus {
    Normal(0),
    Low(1),
    Critical(2),
    Shutdown(3);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
