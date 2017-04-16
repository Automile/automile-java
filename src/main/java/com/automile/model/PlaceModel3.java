package com.automile.model;

import com.automile.model.enums.TripType;
import com.automile.model.enums.TripTypeTrigger;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceModel3 {
    Integer placeId;
    String name;
    String description;
    PositionPointModel positionPoint;
    TripType tripType;
    TripTypeTrigger tripTypeTrigger;
    Integer radius;
    Boolean isEditable;
    Integer organizationNodeId;
    Integer drivesBetweenAnotherPlaceId;
}