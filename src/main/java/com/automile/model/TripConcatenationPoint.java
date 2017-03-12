package com.automile.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripConcatenationPoint {
    Double latitude;
    Double longitude;
    LocalDateTime recordTimeStamp;
    Long recordTimeStampEpochMs;
    Double speed;
    Double RPM;
    Integer headingDegress;
    byte[] hdop;
    //TODO: byte?
    byte[] numberOfSatellites;
}