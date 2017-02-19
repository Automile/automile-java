package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PublishType {
    NUMBER_0(0);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
