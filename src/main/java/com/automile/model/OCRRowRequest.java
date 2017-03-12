package com.automile.model;

import com.automile.model.enums.RequestType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OCRRowRequest {
    String id;
    String imageData;
    RequestType requestType;
}