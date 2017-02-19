package com.automile.model;

import com.automile.model.enums.IMEIDeviceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IMEIConfigDetailModel {
    Integer iMEIConfigId;
    String IMEI;
    Integer vehicleId;
    IMEIDeviceType deviceType;
}

