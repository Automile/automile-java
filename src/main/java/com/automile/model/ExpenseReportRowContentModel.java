package com.automile.model;

import com.automile.model.enums.ContentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseReportRowContentModel {
    Integer expenseReportRowContentId;
    Integer expenseReportRowId;
    ContentType contentType;
    String contentFileName;
}

