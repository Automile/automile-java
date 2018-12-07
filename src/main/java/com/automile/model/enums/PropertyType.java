package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PropertyType {
    PROPERTY_0(0),
    PROPERTY_1(1),
    PROPERTY_2(2),
    PROPERTY_3(3),
    PROPERTY_4(4),
    PROPERTY_5(5),
    PROPERTY_6(6),
    PROPERTY_7(7),
    PROPERTY_8(8),
    PROPERTY_9(9),
    PROPERTY_10(10),
    PROPERTY_11(11),
    PROPERTY_12(12);

    private Integer value;

    public String toString() {return String.valueOf(value); }

}
