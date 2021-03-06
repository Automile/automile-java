package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthenticationType {
    NoneAnonymous(0),
    BasicUsernameAndPassword(1),
    BearerToken(2),
    Salesforce(3);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
