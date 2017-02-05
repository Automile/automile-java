package com.automile.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact2EditModel {
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
    LocalDateTime checkOutDateTimeUtc;
    CheckedInTripTypeEnum checkedInTripType;
    String currencyCode;

    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public enum UnitTypeEnum {
        NUMBER_0(0),
        NUMBER_1(1);

        Integer value;

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

}