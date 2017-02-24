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
public class ExpenseReportRowEditModel {
    Double amountInCurrency;
    Double vATInCurrency;
    String iSO4217CurrencyCode;
    String notes;
    LocalDateTime expenseReportRowDateUtc;
    CategoryType category;
    List<ExpenseReportRowContentUpdateEditModel> expenseReportRowContent = Lists.newArrayList();
    String customCategory;
    Double categoryValue;
    String merchant;
}