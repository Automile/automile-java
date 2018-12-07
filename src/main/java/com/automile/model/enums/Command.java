package com.automile.model.enums;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Command {
    SHUTDOWN(1),
    TRACK(2),
    SLEEP(3);

    private Integer value;

    @Override
    public String toString() { return String.valueOf(value); }
}
