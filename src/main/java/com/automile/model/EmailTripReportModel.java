package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailTripReportModel {
    Integer vehicleId;
    Integer period;
    String toEmail;
    String iSO639LanguageCode;
    Boolean excludeDetailsForPersonalTrips;
    Boolean excludeEnvironmentalAndFuelData;
}