package com.automile.model;

import com.automile.model.enums.DefectStatusType;
import com.automile.model.enums.DefectType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectModel {
    Integer vehicleDefectId;
    Integer vehicleInspectionId;
    DefectType defectType;
    Integer createdByContactId;
    LocalDateTime defectDateUtc;
    String notes;
    DefectStatusType defectStatusType;
    List<VehicleDefectStatusModel> vehicleDefectStatus = Lists.newArrayList();
    List<VehicleDefectAttachmentModel> vehicleDefectAttachments = Lists.newArrayList();
    List<VehicleDefectCommentsModel> vehicleDefectComments = Lists.newArrayList();
}