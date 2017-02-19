package com.automile.model;

import com.automile.model.enums.IMEIDeviceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IMEIConfigEditModel {
    Integer vehicleId;
    IMEIDeviceType iMEIDeviceType;
}

