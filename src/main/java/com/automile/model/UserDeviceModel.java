package com.automile.model;

import com.automile.model.enums.DeviceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDeviceModel {
    Integer userDeviceId;
    String deviceIdentifier;
    String deviceToken;
    DeviceType deviceType;
    String description;
    String deviceName;
}