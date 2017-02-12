package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpenseReportCreateModel {
    Integer vehicleId;
    Integer contactId;
    Integer tripId;
    LocalDateTime expenseReportDateUtc;
    List<ExpenseReportRowCreateModel> expenseReportRows = Lists.newArrayList();
}

