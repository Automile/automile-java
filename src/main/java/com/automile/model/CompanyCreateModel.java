package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyCreateModel {
    String registeredCompanyName;
    String registrationNumber;
    String description;
    Integer createRelationshipToContactId;
}