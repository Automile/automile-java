package com.automile.model;

import com.automile.model.enums.*;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackedAssetModelGET {
    Integer trackedAssetId;
    IMEIDeviceType imeiDeviceType;
    String imei;
    Integer imeiConfigId;
    Status status;
    PositionPointModel lastLocation;
    String lastLocationTimeStamp;
    String lastContact;
    Integer numberOfUnreadNotifications;
    List<PropertyModel> propertyList = Lists.newArrayList();
    List<AssetCommandModel> availableCommandList = Lists.newArrayList();
    String name;
    String imageUrl;
    Integer defaultSleepTimeInMinutes;
    AssetType assetType;
}