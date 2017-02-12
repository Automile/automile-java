package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OwnerType {
    Indvidual(0),
    CorporateUser(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
