package com.automile.model;

import com.automile.model.enums.DestinationType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TriggerMessageHistoryModel {
    Integer triggerMessageHistoryId;
    Integer triggerId;
    LocalDateTime wasSentOn;
    DestinationType destinationType;
    String destinationData;
    String messageData1;
    String messageData2;
}