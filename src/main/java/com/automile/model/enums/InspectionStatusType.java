package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum InspectionStatusType {
    SubmittedWithoutDefects(0),
    SubmittedWithDefects(1),
    Reviewed(2),
    ScheduledRepair(3),
    Archived(4),
    NotReviewed(5),
    SafeToDrive(6),
    NotSafeToDrive(7);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
