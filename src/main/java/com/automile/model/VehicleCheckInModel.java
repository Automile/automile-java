package com.automile.model;

import com.automile.model.enums.DeviceType;
import com.automile.model.enums.TripType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleCheckInModel {
    Integer contactId;
    Integer vehicleId;
    TripType defaultTripType;
    LocalDateTime checkOutAtUtc;
    DeviceType userDeviceType;
    String userDeviceToken;
}