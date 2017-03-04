package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleInspectionCreateModel {
    Integer vehicleId;
    LocalDateTime inspectionDateUtc;
    List<VehicleDefectCreateModel> vehicleDefects = Lists.newArrayList();
}

