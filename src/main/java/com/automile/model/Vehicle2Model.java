package com.automile.model;

import com.automile.model.enums.FuelType;
import com.automile.model.enums.PrivacyPolicyType;
import com.automile.model.enums.TripType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle2Model {
    Integer vehicleId;
    String vehicleIdentificationNumber;
    String numberPlate;
    String make;
    String model;
    Integer ownerContactId;
    Integer ownerCompanyId;
    Double currentOdometerInKilometers;
    String userVehicleIdentificationNumber;
    Integer modelYear;
    String bodyStyle;
    FuelType fuelType;
    TripType defaultTripType;
    Boolean allowAutomaticUpdates;
    PrivacyPolicyType defaultPrivacyPolicyType;
    Integer checkedInContactId;
    Boolean isEditable;
    String makeImageUrl;
    Integer transferIntervalInSeconds;
    Boolean sampleHarshEvents;
    List<String> features = Lists.newArrayList();
    Boolean allowSpeedRecording;
    String nickname;
    Integer categoryColor;
    String tags;
}