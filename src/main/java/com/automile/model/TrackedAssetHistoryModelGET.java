package com.automile.model;

import com.automile.model.enums.HistoryType;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackedAssetHistoryModelGET {
    Integer trackedAssetHistoryId;
    String occuredAt;
    HistoryType historyType;
    PositionPointModel position;
    boolean isNew;
    List<PropertyModel> propertyList = Lists.newArrayList();
}
