package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationNodeModel {
    Integer organizationNodeId;
    String name;
    Integer parentOrganizationNodeId;
    List<OrganizationNodeModel> children = Lists.newArrayList();
    Boolean hasChildren;
}