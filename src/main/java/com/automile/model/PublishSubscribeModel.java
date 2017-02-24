
package com.automile.model;

import com.automile.model.enums.AuthenticationType;
import com.automile.model.enums.PublishType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishSubscribeModel {
    Integer publishSubscribeId;
    String publishToUrl;
    PublishType publishType;
    Integer vehicleId;
    AuthenticationType authenticationType;
    String authenticationData;
}