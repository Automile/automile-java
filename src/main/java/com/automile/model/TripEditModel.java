package com.automile.model;

import com.automile.model.enums.TripType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripEditModel {
    String customCategory;
    TripType tripType;
    List<String> tripTags = Lists.newArrayList();
    Integer lastEditedByContactId;

}

