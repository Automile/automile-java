package com.automile.model;

import com.automile.model.enums.GeofenceType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeofenceCreate2Model {
    String name;
    String description;
    Integer organizationNodeId;
    GeofencePolygon geofencePolygon;
    GeofenceType geofenceType;
    List<GeofenceScheduleAddModel> schedules = Lists.newArrayList();
}