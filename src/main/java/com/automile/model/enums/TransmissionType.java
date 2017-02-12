package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TransmissionType {
    Manual(0),
    Automatic(1),
    Variomatic(2);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
