package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TripType {
    Business(0),
    Personal(1),
    Other(2),
    Auto(3);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
