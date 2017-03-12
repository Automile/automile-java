package com.automile.model;

import com.automile.model.enums.TripDrivingEventType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripConcatenationEvent {
    TripDrivingEventType tripDrivingEventType;
    Double latitude;
    Double longitude;
    LocalDateTime recordTimeStamp;
    Long recordTimeStampEpochMs;
}