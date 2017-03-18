package com.automile.model;

import com.automile.model.enums.MILStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MILEvent {
    LocalDateTime occured;
    MILStatus mILStatus;
    Integer mILDistance;
    Double cLRDistanceUntilToday;
    short numberOfDTCs;
    Double latitude;
    Double longitude;
}