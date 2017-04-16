package com.automile.model;

import com.automile.model.enums.DeliveryType;
import com.automile.model.enums.DestinationType;
import com.automile.model.enums.TriggerType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TriggerDetailModel2 {
    Integer triggerId;
    Integer iMEIConfigId;
    TriggerType triggerType;
    String triggerTypeData;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    LocalDateTime mutedUntilDateTime;
    Integer mutedForAdditionalSeconds;
    DestinationType destinationType;
    String destinationData;
    DeliveryType deliveryType;
    Integer aPIClientId;
    LocalDateTime createdAt;
    String triggerTypeData2;
    Integer organizationNodeId;
}