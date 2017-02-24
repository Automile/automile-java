package com.automile.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContactModel {
    Integer contactId;
    String firstName;
    String lastName;
    String emailAddress;
}