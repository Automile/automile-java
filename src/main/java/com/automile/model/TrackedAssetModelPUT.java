package com.automile.model;
import com.automile.model.enums.AssetType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackedAssetModelPUT {
    String name;
    String imageUrl;
    Integer defaultSleepTypeInMinutes;
    AssetType assetType;
}
