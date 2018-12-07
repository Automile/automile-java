package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    STATUS_0(0),
    STATUS_1(1),
    STATUS_2(2),
    STATUS_3(3),
    STATUS_4(4),
    STATUS_5(5);

    private Integer value;

    public String toString() { return String.valueOf(value); }
}
