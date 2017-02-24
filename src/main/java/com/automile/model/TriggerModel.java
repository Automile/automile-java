package com.automile.model;

import com.automile.model.enums.DestinationType;
import com.automile.model.enums.TriggerType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TriggerModel {
    Integer triggerId;
    Integer iMEIConfigId;
    TriggerType triggerType;
    DestinationType destinationType;
    String destinationData;
    String triggerTypeData;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    LocalDateTime mutedUntilDateTime;
    Integer mutedForAdditionalSeconds;
    LocalDateTime createdAt;
    String triggerTypeData2;
}