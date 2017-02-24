package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryType {
    Once(0),
    EveryTrip(1),
    Continously(2),
    Other(3);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}