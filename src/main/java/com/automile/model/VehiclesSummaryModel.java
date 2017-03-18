package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehiclesSummaryModel {
    List<DistanceReportModel> distanceReports = Lists.newArrayList();
    List<FuelReportModel> fuelReports = Lists.newArrayList();
    List<TravelTimeReportModel> travelTimeReports = Lists.newArrayList();
    List<CO2ReportModel> cO2Reports = Lists.newArrayList();
    List<IdleTimeReportModel> idleTimeReports = Lists.newArrayList();
}