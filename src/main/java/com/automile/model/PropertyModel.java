package com.automile.model;
import com.automile.model.enums.PropertyType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PropertyModel {
    PropertyType propertyType;
    String propertyName;
    String valueType;
    String value;
    String unit;
}
