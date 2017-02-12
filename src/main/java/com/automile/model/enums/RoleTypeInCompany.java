package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleTypeInCompany {
    Administrator(0),
    FleetManager(1),
    DispatchmentAgent(2),
    CommercialDriver(3),
    BusinessDriver(4),
    PayrollTeam(5);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
