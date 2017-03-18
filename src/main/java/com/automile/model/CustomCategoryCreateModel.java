package com.automile.model;

import com.automile.model.enums.CustomCategoryType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomCategoryCreateModel {
    String value;
    CustomCategoryType customCategoryType;
}