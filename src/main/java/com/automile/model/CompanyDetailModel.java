package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyDetailModel {
    Integer companyId;
    String registeredCompanyName;
    String registrationNumber;
    String description;
    LocalDateTime lastModified;
    LocalDateTime created;
}