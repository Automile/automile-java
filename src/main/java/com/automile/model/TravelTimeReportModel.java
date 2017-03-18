package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TravelTimeReportModel {
    Integer businessTravelTimeInMinutes;
    Integer personalTravelTimeInMinutes;
    Integer otherTravelTimeInMinutes;
    Integer period;
}