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
public class Contact2DetailModel {
    Integer contactId;
    String firstName;
    String lastName;
    String emailAddress;
    String description;
    String cultureName;
    String countryCodeIso1366;
    Integer defaultVehicleId;
    UnitTypeEnum unitType;
    Boolean subscribeToNewsLetter;
    String mobilePhoneNumber;
    MapTypeEnum mapType;
    Integer checkedIntoVehicleId;
    LocalDateTime checkedInDateTimeUtc;
    LocalDateTime checkOutDateTimeUtc;
    CheckedInTripTypeEnum checkedInTripType;
    List<CompanyRolesEnum> companyRoles = Lists.newArrayList();
    String profileImageUrl;
    String currencyCode;
    List<CompanyScopesModel> companiesScopes = Lists.newArrayList();
    Boolean isLocked;
    List<CustomCategoryModel> customCategories = Lists.newArrayList();

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum UnitTypeEnum {
        NUMBER_0(0),
        NUMBER_1(1);

        private int value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum MapTypeEnum {
        NUMBER_0(0),
        NUMBER_1(1),
        NUMBER_2(2);

        Integer value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum CheckedInTripTypeEnum {
        NUMBER_0(0),
        NUMBER_1(1),
        NUMBER_2(2),
        NUMBER_3(3);

        Integer value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum CompanyRolesEnum {
        NUMBER__0(0),
        NUMBER__1(1),
        NUMBER__2(2),
        NUMBER__3(3),
        NUMBER__4(4),
        NUMBER__5(5);

        Integer value;

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

}