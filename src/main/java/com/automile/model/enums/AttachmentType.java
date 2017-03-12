package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AttachmentType {
    Image(0),
    PDF(1),
    DOCX(2),
    EXCEL(3);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}