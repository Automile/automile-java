package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskMessageCreateModel {
    Integer taskId;
    String messageText;
    PositionModel position;
}