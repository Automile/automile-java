package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PrivacyPolicyType {
    SaveNoRoute(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
