package com.automile.model;

import com.automile.model.enums.FuelType;
import com.automile.model.enums.PrivacyPolicyType;
import com.automile.model.enums.TripType;
import com.automile.model.enums.VehicleRelationshipType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle2CreateModel {
    String numberPlate;
    String make;
    String model;
    Integer modelYear;
    String bodyStyle;
    Integer ownerContactId;
    Integer ownerCompanyId;
    Integer createRelationshipToId;
    VehicleRelationshipType vehicleRelationshipType;
    String userVehicleIdentificationNumber;
    FuelType fuelType;
    TripType defaultTripType;
    Boolean allowAutomaticUpdates;
    PrivacyPolicyType defaultPrivacyPolicyType;
    String nickname;
    String tags;
    Integer categoryColor;
}