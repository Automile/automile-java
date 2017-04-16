package com.automile.model;

import com.automile.model.enums.GeofenceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeofenceRecordReportModel {
    Integer tripId;
    Integer geofenceId;
    String geofenceName;
    GeofenceType geofenceType;
    Boolean isInside;
    LocalDateTime eventDateTime;
    VehicleTinyModel vehicleTinyModel;
}