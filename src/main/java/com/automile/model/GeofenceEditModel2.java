package com.automile.model;

import com.automile.model.enums.GeofenceType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeofenceEditModel2 {
    String name;
    String description;
    GeofencePolygon geofencePolygon;
    GeofenceType geofenceType;
    Integer organizationNodeId;
    List<GeofenceScheduleEditModel> schedules = Lists.newArrayList();
}