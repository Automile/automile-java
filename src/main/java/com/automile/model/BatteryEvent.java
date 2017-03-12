package com.automile.model;

import com.automile.model.enums.BatteryStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatteryEvent {
    LocalDateTime occured;
    Double latitude;
    Double longitude;
    BatteryStatus batteryStatus;
}