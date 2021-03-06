package com.automile.model;

import com.automile.model.enums.TaskStatusType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskEditModel {
    TaskStatusType taskStatusType;
    String title;
}