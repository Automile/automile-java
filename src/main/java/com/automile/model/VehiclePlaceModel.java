package com.automile.model;

import com.automile.model.enums.TripType;
import com.automile.model.enums.TripTypeTrigger;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehiclePlaceModel {
    Integer vehiclePlaceId;
    Integer vehicleId;
    Integer placeId;
    String description;
    TripType tripType;
    TripTypeTrigger tripTypeTrigger;
    Integer radius;
    Integer drivesBetweenAnotherPlaceId;
}