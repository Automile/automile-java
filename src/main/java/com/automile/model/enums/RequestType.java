package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RequestType {
    decimal(0),
    text(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
