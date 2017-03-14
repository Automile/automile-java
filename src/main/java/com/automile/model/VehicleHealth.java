package com.automile.model;

import com.automile.model.enums.BatteryStatus;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleHealth {
    Integer vehicleId;
    Integer periodFrom;
    Integer periodTo;
    BatteryStatus lastBatteryStatus;
    BatteryEvent lastBatteryWarning;
    List<BatteryEvent> batteryEventsForSelectedPeriod = Lists.newArrayList();
    MILEvent lastMILEvent;
    List<MILEvent> mILEventsForSelectedPeriod = Lists.newArrayList();
    DTCEvent lastDTCEvent;
    DTCEvent lastPendingDTCEvent;
    List<DTCEvent> dTCEventsForSelectedPeriod = Lists.newArrayList();
    List<DTCEvent> pendingDTCEventsForSelectedPeriod = Lists.newArrayList();
}