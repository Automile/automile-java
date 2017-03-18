package com.automile.model;

import com.automile.model.enums.LastLocationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleStatusModel {
    Integer vehicleId;
    LocalDateTime lastPositionUtc;
    Double lastKnownLatitude;
    Double lastKnownLongitude;
    Boolean isDriving;
    String makeImageUrl;
    Integer parkedForNumberOfSeconds;
    Integer drivingForNumberOfSeconds;
    String lastKnownFormattedAddress;
    String lastKnownStreetAddress;
    String lastKnownCity;
    Integer lastTripId;
    LastLocationType lastLocationType;
}