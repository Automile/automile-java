package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskMessageModel {
    Integer taskMessageId;
    Integer sentByContactId;
    Integer toContactId;
    String messageText;
    LocalDateTime messageSentAtUtc;
    String sentByContactName;
    String toContactName;
    Boolean messageIsRead;
    PositionModel position;
}

