package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FuelReportModel {
    Double businessFuelInLiters;
    Double personalFuelInLiters;
    Double otherFuelInLiters;
    Integer period;
}