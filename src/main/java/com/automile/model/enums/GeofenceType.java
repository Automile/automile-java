package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GeofenceType {
    Outside(0),
    Inside(1),
    OutsideAndInside(2);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
