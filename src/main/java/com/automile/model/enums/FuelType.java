package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FuelType {
    Diesel(0),
    Petrol(1),
    Ethanol(2),
    Gas(3),
    Hybrid(4),
    Electric(5),
    Methane(6);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
