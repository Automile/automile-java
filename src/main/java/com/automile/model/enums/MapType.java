package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MapType {
    Mapbox(0),
    Google(1),
    Apple(2);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
