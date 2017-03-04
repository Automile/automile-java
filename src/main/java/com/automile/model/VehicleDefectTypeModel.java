package com.automile.model;

import com.automile.model.enums.DefectType;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectTypeModel {
    DefectType defectType;
    Map<String, String> localizedName = Maps.newHashMap();
}