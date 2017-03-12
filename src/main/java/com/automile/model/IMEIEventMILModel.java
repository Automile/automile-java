package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IMEIEventMILModel {
    Boolean milStatus;
    Integer numberOfDTCs;
    Integer mILDistance;
    Integer cLRDistance;
    Integer tripDistance;
    Integer mILTime;
    Integer cLRTime;
    Integer tripTime;
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