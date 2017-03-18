package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LastLocationType {
    //TODO: text
    NUMBER_0(0),
    NUMBER_1(1),
    NUMBER_2(2);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}