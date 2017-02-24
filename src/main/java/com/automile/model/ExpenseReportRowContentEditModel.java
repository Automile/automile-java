package com.automile.model;

import com.automile.model.enums.ContentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseReportRowContentEditModel {
    String data;
    ContentType contentType;
}