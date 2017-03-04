package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PublishType {
    JsonDefault(0);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
