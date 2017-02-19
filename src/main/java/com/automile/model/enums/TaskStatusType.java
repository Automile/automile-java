package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskStatusType {
    Open(0),
    Closed(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
