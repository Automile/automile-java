package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyVehicleModel {
    Integer companyVehicleId;
    Integer companyId;
    Integer vehicleId;
}