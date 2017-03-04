package com.automile.model;

import com.automile.model.enums.InspectionStatusType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleInspectionEditModel {
    Integer vehicleId;
    InspectionStatusType inspectionStatusType;
    List<VehicleDefectEditModel> vehicleDefects = Lists.newArrayList();
}