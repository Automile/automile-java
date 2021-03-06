package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DefectType {
    Other(0),
    AirCompressor(1),
    AirLines(2),
    Battery(3),
    BrakeAccessories(4),
    Brakes(5),
    Clutch(6),
    Defroster(7),
    DriveLine(8),
    Engine(9),
    FifthWheel(10),
    FrontAxle(11),
    FuelTanks(12),
    Heater(13),
    Horn(14),
    Lights(15),
    Mirrors(16),
    Muffler(17),
    OilPressure(18),
    OnBoardRecorder(19),
    Radiator(20),
    RearEnd(21),
    Reflectors(22),
    SafetyEquipment(23),
    Springs(24),
    Starter(25),
    Steering(26),
    Tires(27),
    Transmission(28),
    Wheel(29),
    Windows(30),
    WindshieldWipers(31);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
