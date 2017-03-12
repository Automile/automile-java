package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
//TODO: names
public enum RequestType {
    NUMBER_0(0),
    NUMBER_1(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
