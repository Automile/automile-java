package com.automile.model;

import com.automile.model.enums.AttachmentType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleDefectAttachmentCreateModel {
    AttachmentType attachmentType;
    String data;
}