package com.automile.model;

import com.automile.model.enums.TripType;
import com.automile.model.enums.TripTypeTrigger;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place3CreateModel {
    String name;
    Integer radius;
    PositionPointModel positionPoint;
    String description;
    TripType tripType;
    TripTypeTrigger tripTypeTrigger;
    Integer organizationNodeId;
    Integer drivesBetweenAnotherPlaceId;
}