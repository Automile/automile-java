package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressModel {
    Integer addressId;
    String companyName;
    String contactNameAtAddress;
    String addressLine2;
    String addressLine3;
    String addressLine4;
    String zipCode;
    String city;
    String stateOrProvince;
    String iSO3166CountryCode;
}