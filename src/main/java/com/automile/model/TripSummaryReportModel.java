package com.automile.model;

import com.automile.model.enums.TripType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripSummaryReportModel {
    Integer reportStartPeriod;
    Integer reportEndPeriod;
    Integer vehicleId;
    TripType tripType;
    Double distanceInKilometers;
    Integer travelTimeInMinutes;
    Double fuelInLiters;
}