package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDetailModel {
    Integer taskId;
    Integer createdByContactId;
    String createdByContactName;
    Integer taskAcquiredByContactId;
    String taskAcquiredByContactName;
    List<TaskMessageModel> taskMessages = Lists.newArrayList();
}

