package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IMEIEventDTCModel {
    List<DTC> dtcs = Lists.newArrayList();
    String description;
    LocalDateTime timeStamp;
    Integer timeZone;
    String eventType;
    Double positionLongitude;
    Double positionLatitude;
    String positionFormattedAddress;
    Integer cellTower;
    Integer cellTowerTimeZone;
    String vehicleIdentificationNumber;
    String IMEI;
    String deviceSerialNumber;
}