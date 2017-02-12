package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UnitType {
    Metric(0),
    Imperial(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
