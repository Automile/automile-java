package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccelerometerEventType {
    //TODO:DEC?
    PositiveXAxis(0x01),
    NegativeXAxis(0x10),
    PositiveYAxis(0x02),
    NegativeYAxis(0x20),
    PositiveZAxis(0x04),
    NegativeZAxis(0x40),
    XHistogramData(0x08),
    YHistogramData(0x80),
    ZHistogramData(0x88),
    ATHistogramData(0x00);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
