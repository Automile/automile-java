package com.automile.model;

import com.automile.model.enums.DefectStatusType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectStatusModel {
    Integer vehicleDefectStatusId;
    Integer vehicleDefectId;
    Integer createdByContactId;
    DefectStatusType defectStatus;
    LocalDateTime defectStatusDateUtc;
}