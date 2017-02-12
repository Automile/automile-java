package com.automile.model;

import com.automile.model.enums.RoleTypeInCompany;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact2Model {
    Integer contactId;
    String firstName;
    String lastName;
    String emailAddress;
    String mobilePhoneNumber;
    String profileImageUrl;
    LocalDateTime checkedInDateTimeUtc;
    Integer checkedIntoVehicleId;
    Boolean isManager;
    List<RoleTypeInCompany> companyRoles = Lists.newArrayList();
    String currencyCode;
}