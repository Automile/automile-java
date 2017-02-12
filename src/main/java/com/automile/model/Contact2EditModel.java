package com.automile.model;

import com.automile.model.enums.MapType;
import com.automile.model.enums.TripType;
import com.automile.model.enums.UnitType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact2EditModel {
    String firstName;
    String lastName;
    String emailAddress;
    String description;
    String cultureName;
    String countryCodeIso1366;
    Integer defaultVehicleId;
    UnitType unitType;
    Boolean subscribeToNewsLetter;
    String mobilePhoneNumber;
    MapType mapType;
    LocalDateTime checkOutDateTimeUtc;
    TripType checkedInTripType;
    String currencyCode;
}