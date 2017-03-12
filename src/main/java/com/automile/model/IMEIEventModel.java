package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IMEIEventModel {
    Integer iMEIEventId;
    String IMEI;
    String eventType;
    LocalDateTime timeStamp;
}