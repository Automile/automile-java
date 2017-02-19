package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthenticationType {
    NUMBER_0(0),
    NUMBER_1(1),
    NUMBER_2(2),
    NUMBER_3(3);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
