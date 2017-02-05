package com.automile.model;

import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyContactDetailModel {
    Integer companyContactId;
    Integer companyId;
    String companyName;
    Integer contactId;
    String contactName;
    LocalDateTime lastModified;
    LocalDateTime created;
    List<String> scopes = Lists.newArrayList();
}