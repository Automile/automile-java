package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripGeoModel {
    LocalDateTime recordTimeStamp;
    Double latitude;
    Double longitude;
    Integer headingDegrees;
    byte[] numberOfSatellites;
    byte[] HDOP;
}