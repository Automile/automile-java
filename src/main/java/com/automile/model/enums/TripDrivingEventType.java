package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TripDrivingEventType {
    TurnForgiving(0),
    TurnAgressive(1),
    TurnVeryAgressive(2),
    DecelerationForgiving(3),
    DecelerationAgressive(4),
    DecelerationVeryAgressive(5),
    AccelerationForgiving(6),
    AccelerationAgressive(7),
    AccelerationVeryAgressive(8),
    RoadConditionForgiving(9),
    RoadConditionWorse(10),
    RoadConditionDisaster(11),
    AccidentSide(12),
    AccidentFrontBack(13);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
