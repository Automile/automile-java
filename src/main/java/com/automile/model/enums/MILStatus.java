package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MILStatus {
    Off(0),
    On(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
