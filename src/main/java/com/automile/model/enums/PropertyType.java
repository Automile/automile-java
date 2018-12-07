package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PropertyType {
    BATTER_YLEVEL(0),
    STATUS(1),
    SLEEP_UNTIL(2),
    TEMPERATURE(3),
    TRACK_UNTIL(5),
    TRIP_ID(6),
    VEHICLE_ID(7),
    LAST_TRIP_ID(8),
    HAS_EXTERNAL_POWER(9),
    EXTERNAL_POWER(10),
    OPERATING_TIME(11),
    CONTACT_ID(12);

    private Integer value;

    public String toString() {return String.valueOf(value); }

}
