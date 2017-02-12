package com.automile.model;

import com.automile.model.enums.MapType;
import com.automile.model.enums.RoleTypeInCompany;
import com.automile.model.enums.TripType;
import com.automile.model.enums.UnitType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact2DetailModel {
    Integer contactId;
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
    Integer checkedIntoVehicleId;
    LocalDateTime checkedInDateTimeUtc;
    LocalDateTime checkOutDateTimeUtc;
    TripType checkedInTripType;
    List<RoleTypeInCompany> companyRoles = Lists.newArrayList();
    String profileImageUrl;
    String currencyCode;
    List<CompanyScopesModel> companiesScopes = Lists.newArrayList();
    Boolean isLocked;
    List<CustomCategoryModel> customCategories = Lists.newArrayList();


}