package com.automile.model;

import com.automile.model.enums.MapType;
import com.automile.model.enums.UnitType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact2CreateModel {
    String firstName;
    String lastName;
    String emailAddress;
    String description;
    Integer createRelationshipToCompanyId;
    String cultureName;
    String countryCodeIso1366;
    Integer defaultVehicleId;
    UnitType unitType;
    Boolean subscribeToNewsLetter;
    String mobilePhoneNumber;
    MapType mapType;
    String currencyCode;
}