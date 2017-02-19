package com.automile.model;

import com.automile.model.enums.AuthenticationType;
import com.automile.model.enums.PublishType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishSubscribeEditModel {
    String publishToUrl;
    PublishType publishType;
    Integer vehicleId;
    AuthenticationType authenticationType;
    String authenticationData;
}