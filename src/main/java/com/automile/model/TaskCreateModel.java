package com.automile.model;

import com.automile.model.enums.TaskType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskCreateModel {
    TaskType taskType;
    Integer toContactId;
    String taskMessageText;
    PositionModel position;
}

