package com.automile.model;

import com.automile.model.enums.FuelType;
import com.automile.model.enums.PrivacyPolicyType;
import com.automile.model.enums.TripType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle2EditModel {
    String numberPlate;
    String make;
    String model;
    Integer modelYear;
    String bodyStyle;
    Integer ownerContactId;
    Integer ownerCompanyId;
    String userVehicleIdentificationNumber;
    FuelType fuelType;
    TripType defaultTripType;
    Boolean allowAutomaticUpdates;
    String frontTyre;
    String rearTyre;
    String frontWheelRim;
    String rearWheelRim;
    Double odometerInKilometers;
    Boolean hasOdometerChanged;
    PrivacyPolicyType defaultPrivacyPolicyType;
    Boolean updateFromTransportstyrelsen;
    Boolean allowSpeedRecording;
    String nickname;
    String tags;
    Integer categoryColor;

}

