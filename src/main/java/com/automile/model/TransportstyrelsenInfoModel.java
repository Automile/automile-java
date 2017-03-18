package com.automile.model;

import com.automile.model.enums.FuelType;
import com.automile.model.enums.TransmissionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransportstyrelsenInfoModel {
    Boolean hasInformation;
    String make;
    String model;
    String makeImageUrl;
    Integer modelYear;
    String bodyStyle;
    FuelType fuelType;
    TransmissionType transmissionType;
    Boolean trailerHitch;
}