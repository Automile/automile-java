package com.automile.model;

import com.automile.model.enums.InspectionStatusType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleInspectionStatusModel {
    Integer vehicleInspectionStatusId;
    Integer vehicleInspectionId;
    Integer createdByContactId;
    InspectionStatusType inspectionStatusType;
    LocalDateTime statusDateUtc;
}