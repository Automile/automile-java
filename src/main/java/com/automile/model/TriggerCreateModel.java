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
public class TriggerCreateModel {
    Integer iMEIConfigId;
    TriggerType triggerType;
    String triggerTypeData;
    String triggerTypeData2;
    LocalDateTime validFrom;
    LocalDateTime validTo;
    DestinationType destinationType;
    String destinationData;
    DeliveryType deliveryType;
}