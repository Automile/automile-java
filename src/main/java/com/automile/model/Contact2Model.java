package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
    List<CompanyRolesEnum> companyRoles = Lists.newArrayList();
    String currencyCode;

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum CompanyRolesEnum {
        NUMBER_0(0),
        NUMBER_1(1),
        NUMBER_2(2),
        NUMBER_3(3),
        NUMBER_4(4),
        NUMBER_5(5);

        Integer value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}