package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VehicleRelationshipType {
    VehicleToContact(0),
    VehicleToCompany(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
