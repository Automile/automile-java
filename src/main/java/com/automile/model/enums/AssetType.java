package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AssetType {
    ASSET_TYPE_0(0),
    ASSET_TYPE_1(1),
    ASSET_TYPE_2(2),
    ASSET_TYPE_3(3),
    ASSET_TYPE_4(4),
    ASSET_TYPE_5(5),
    ASSET_TYPE_6(6),
    ASSET_TYPE_255(255);

    private Integer value;

    public String toString() { return String.valueOf(value); }
}
