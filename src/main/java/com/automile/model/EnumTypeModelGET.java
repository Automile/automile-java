package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnumTypeModelGET {
    String memberName;
    String enumType;
    Object enumValueList = new Object();
}
