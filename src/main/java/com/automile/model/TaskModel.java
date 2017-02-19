package com.automile.model;

import com.automile.model.enums.TaskStatusType;
import com.automile.model.enums.TaskType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskModel {
    Integer taskId;
    Integer createdByContactId;
    String createdByContactName;
    Integer taskAcquiredByContactId;
    String taskAcquiredByContactName;
    Integer lastMessageSentByContactId;
    String lastMessageSentByContactName;
    String lastMessageShortText;
    LocalDateTime lastMessageDateUtc;
    Boolean lastMessageIsRead;
    String createdByContactImageUrl;
    String taskAcquiredByContactImageUrl;
    TaskStatusType taskStatusType;
    TaskType taskType;
    Integer unreadTaskMessages;
}

