package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripConcatenationData {
    LocalDateTime recordTimeStamp;
    Long recordTimeStampEpochMs;
    Double value;
}

