package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskType {
    Message(0),
    Task(1);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
