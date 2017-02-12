package com.automile.model;

import com.automile.model.enums.CategoryType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseReportRowModel {
    Integer expenseReportRowId;
    Integer expenseReportId;
    Double amountInCurrency;
    Double vATInCurrency;
    String iSO4217CurrencyCode;
    String notes;
    LocalDateTime expenseReportRowDateUtc;
    List<ExpenseReportRowContentModel> expenseReportRowContent = Lists.newArrayList();
    private CategoryType category;

}

