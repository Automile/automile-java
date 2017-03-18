package com.automile.model;

import com.automile.model.enums.UnitType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationEditModel {
    String organizationName;
    String legalIdentificationNumber;
    String vATNumber;
    AddressModel billingAddress;
    AddressModel shipmentAddress;
    String culture;
    UnitType unitType;
}