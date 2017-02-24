package com.automile.model;

import com.automile.model.enums.TripType;
import com.automile.model.enums.TripTypeTrigger;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place2CreateModel {
    String name;
    List<Integer> vehicleIds = Lists.newArrayList();
    Integer radius;
    PositionPointModel positionPoint;
    String description;
    TripType tripType;
    TripTypeTrigger tripTypeTrigger;
    Integer drivesBetweenAnotherPlaceId;
}