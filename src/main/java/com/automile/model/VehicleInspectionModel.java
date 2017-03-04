package com.automile.model;

import com.automile.model.enums.InspectionStatusType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleInspectionModel {
    Integer vehicleInspectionId;
    Integer vehicleId;
    Integer createdByContactId;
    InspectionStatusType inspectionStatusType;
    LocalDateTime inspectionDateUtc;
    List<VehicleDefectModel> vehicleDefects = Lists.newArrayList();
    List<VehicleInspectionStatusModel> inspectionStatus = Lists.newArrayList();
}