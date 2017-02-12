package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContentType {
    Image(0),
    Pdf(1),
    Voice(2),
    Excel(3),
    Word(4);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
