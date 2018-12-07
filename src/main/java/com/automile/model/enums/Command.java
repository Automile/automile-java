package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Command {
    COMMAND_1(1),
    COMMAND_2(2),
    COMMAND_3(3);

    private Integer value;

    @Override
    public String toString() { return String.valueOf(value); }
}
