package com.automile.model;

import com.automile.model.enums.ResetPasswordType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPasswordUserModel {
    String email;
    ResetPasswordType resetPasswordType;
}
