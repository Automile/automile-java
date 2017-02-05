package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyInfoModel {
    String companyName;
    String mobilePhoneNumber;
    String addressLine1;
    String city;
    String postalNumber;
    String country;
    Integer numberOfVehicles;
}