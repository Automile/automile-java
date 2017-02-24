package com.automile.model;

import com.automile.model.enums.ContentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseReportRowContentUpdateEditModel {
    Integer expenseReportRowContentId;
    Integer expenseReportRowId;
    String data;
    ContentType contentType;
}