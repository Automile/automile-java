package com.automile.model;

import com.automile.model.enums.DefectStatusType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectStatusCreateModel {
    private DefectStatusType defectStatus;
}