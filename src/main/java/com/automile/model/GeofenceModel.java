package com.automile.model;

import com.automile.model.enums.GeofenceType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeofenceModel {
    Integer geofenceId;
    String name;
    String description;
    GeofencePolygon geofencePolygon;
    Boolean isEditable;
    GeofenceType geofenceType;
    List<GeofenceScheduleModel> schedules = Lists.newArrayList();
}