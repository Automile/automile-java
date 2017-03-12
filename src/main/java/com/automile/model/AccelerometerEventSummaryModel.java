package com.automile.model;

import com.automile.model.enums.AccelerometerEventType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccelerometerEventSummaryModel {
    String eventId;
    AccelerometerEventType accelerometerEventType;
    LocalDateTime recordTimeStamp;
}