package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AssetType {
    VEHICLE(0),
    TRAILER(1),
    ATV(2),
    BOAT(3),
    EXCAVATOR(4),
    CARGO(5),
    ASSET_TYPE_6(6),
    UNKNOWN(255);

    private Integer value;

    public String toString() { return String.valueOf(value); }
}
