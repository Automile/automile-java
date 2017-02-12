package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoryType {
    Fuel(0),
    SpareParts(1),
    Parking(2),
    Other(3),
    Toll(4);
    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
