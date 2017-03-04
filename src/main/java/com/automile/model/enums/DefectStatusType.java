package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DefectStatusType {
    NotResolved(0),
    Resolved(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
