package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectCommentsModel {
    Integer vehicleDefectCommentId;
    Integer vehicleDefectId;
    Integer createdByContactId;
    String comment;
    LocalDateTime commentDateUtc;
}