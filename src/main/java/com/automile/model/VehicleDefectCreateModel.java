package com.automile.model;

import com.automile.model.enums.DefectType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectCreateModel {
    DefectType defectType;
    String notes;
    List<VehicleDefectStatusCreateModel> vehicleDefectStatus = Lists.newArrayList();
    List<VehicleDefectAttachmentCreateModel> vehicleDefectAttachments = Lists.newArrayList();
    List<VehicleDefectCommentsCreateModel> vehicleDefectComments = Lists.newArrayList();
}