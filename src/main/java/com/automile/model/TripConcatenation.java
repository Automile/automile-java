package com.automile.model;

import com.automile.model.enums.TripType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripConcatenation {
    Integer tripId;
    Integer vehicleId;
    String vehicleName;
    List<Integer> mergedFromTripIds = Lists.newArrayList();
    List<TripConcatenationSpeedGroup> speedGroups = Lists.newArrayList();
    List<TripConcatenationSpeedPoint> speedPoints = Lists.newArrayList();
    List<TripConcatenationPoint> rawPoints = Lists.newArrayList();
    List<TripConcatenationSimplePoint> snappedToRoadPoints = Lists.newArrayList();
    List<TripConcatenationEvent> drivingEvents = Lists.newArrayList();
    List<TripConcatenationData> speedData = Lists.newArrayList();
    List<TripConcatenationData> speedLimitData = Lists.newArrayList();
    List<TripConcatenationData> rPMData = Lists.newArrayList();
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    Integer tripTimeZone;
    String startFormattedAddress;
    String endFormattedAddress;
    String startCustomAddress;
    String endCustomAddress;
    Double distance;
    Double fuelConsumption;
    Integer lengthInMinutes;
    Integer driverContactId;
    String driverName;
    Integer idleRPMAverage;
    Integer idleTimeInSecondsForAllTrip;
    Integer idleTimeInSecondsFromStart;
    Double cO2Emission;
    Double maxSpeed;
    Integer maxRPM;
    Integer parkedForMinutesUntilNextTrip;
    TripConcatenationStartEndPoint startPoint;
    TripConcatenationStartEndPoint endPoint;
    String notes;
    TripType tripType;
    Double averageSpeedInKilometersPerHour;
    String customCategory;
    Boolean hideStartRoute;
    Boolean hideEndRoute;
}