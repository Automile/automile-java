package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripStartEndGeoModel {
    Double startLatitude;
    Double startLongitude;
    Double endLatitude;
    Double endLongitude;
}

