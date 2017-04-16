package com.automile.model;

import com.automile.model.enums.FuelType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleTinyModel {
    Integer vehicleId;
    String numberPlate;
    String make;
    String model;
    String friendlyName;
    FuelType fuelType;
    Integer modelYear;
    String imageUrlMake;
    String userFriendlyName;
}