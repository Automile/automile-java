package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripConcatenationSpeedGroup {
    String speedGroup;
    Boolean speedingForMoreThan30Seconds;
    Double distanceOfSpeeding;
    Integer distanceInSeconds;
    //TODO: byte?
    byte[] thresholdType;
    List<TripConcatenationSpeedPoint> speedPoints = Lists.newArrayList();
}

