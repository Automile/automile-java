package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyScopesModel {
    Integer companyId;
    List<Integer> vehicleIds = Lists.newArrayList();
    List<String> scopes = Lists.newArrayList();
}