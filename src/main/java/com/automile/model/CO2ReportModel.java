package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CO2ReportModel {
    Double businessCO2InGrams;
    Double personalCO2InGrams;
    Double otherCO2InGrams;
    Integer period;
}