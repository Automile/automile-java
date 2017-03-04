package com.automile.model;

import com.automile.model.enums.DefectStatusType;
import com.automile.model.enums.DefectType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectEditModel {
    Integer vehicleDefectId;
    DefectType defectType;
    String notes;
    DefectStatusType defectStatusType;
    List<VehicleDefectAttachmentEditModel> vehicleDefectAttachments = Lists.newArrayList();
    List<VehicleDefectCommentEditModel> vehicleDefectComments = Lists.newArrayList();
}