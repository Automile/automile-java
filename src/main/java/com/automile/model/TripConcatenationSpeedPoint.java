package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripConcatenationSpeedPoint {
    Double speed;
    Double speedLimit;
    Double latitude;
    Double longitude;
    LocalDateTime recordTimeStamp;
    Long recordTimeStampEpochMs;
}