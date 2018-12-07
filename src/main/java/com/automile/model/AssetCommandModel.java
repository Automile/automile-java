package com.automile.model;
import com.automile.model.enums.Command;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssetCommandModel {
    Command command;
    String endpoint;
    String availableUntil;
    List<Integer> trackingIntervalList = Lists.newArrayList();
}
