package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OCRRowResult {
    String id;
    Double amount;
    String iSO4217CurrencyCode;
    String exception;
    String text;
}