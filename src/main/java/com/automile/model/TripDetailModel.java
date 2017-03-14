package com.automile.model;

import com.automile.model.enums.TripType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripDetailModel {
    Integer tripId;
    String IMEI;
    LocalDateTime tripStartDateTime;
    Integer tripStartTimeZone;
    Integer tripStartODOMeter;
    Integer tripNumber;
    byte[] numberOfSupportedPIDs;
    String vehicleIdentificationNumber;
    String vechicleProtocol;
    LocalDateTime tripEndDateTime;
    Integer tripEndTimeZone;
    Integer tripEndODOMeter;
    String tripStartFormattedAddress;
    String tripEndFormattedAddress;
    String tripStartCustomAddress;
    String tripEndCustomAddress;
    TripType tripType;
    String tripTags;
    Integer vehicleId;
    Double fuelInLiters;
    Double tripLengthInKilometers;
    Integer tripLengthInMinutes;
    Integer idleTimeInSecondsAllTrip;
    Integer idleTimeInSecondsFromStart;
    Integer idleRPMMax;
    byte[] maxSpeed;
    Integer maxRPM;
    Double cO2EmissionInGrams;
    Double odometerInKilometersAfterTripEnded;
    Double averageSpeedInKilometersPerHour;
    Double tripStartOutsideTemperatureInCelsius;
    Integer driverContactId;
    Boolean hasDrivingEvents;
    String customCategory;
    Boolean hideStartRoute;
    Boolean hideEndRoute;
}