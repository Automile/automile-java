package com.automile.model;

import com.automile.model.enums.TripType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripModel {
    Integer tripId;
    LocalDateTime tripStartDateTime;
    LocalDateTime tripEndDateTime;
    Double distanceKilometers;
    Double fuelConsumptionInLiters;
    String tripStartFormattedAddress;
    String tripEndFormattedAddress;
    String tripStartCustomAddress;
    String tripEndCustomAddress;
    Integer vehicleId;
    TripType tripType;
    Integer driverContactId;
    Boolean hasSpeedingViolation;
    Boolean hasIdlingEvent;
    Boolean hasAccelerationEvent;
    Boolean hasAccident;
    Boolean hasTurningEvent;
    Boolean hasBrakingEvent;
    Integer parkedForMinutesUntilNextTrip;
    Boolean hasDrivingEvents;
    String customCategory;
    String tripTags;
    Boolean hideStartRoute;
    Boolean hideEndRoute;
}