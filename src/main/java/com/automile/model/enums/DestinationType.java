package com.automile.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DestinationType {
    Email(1),
    Sms(2),
    HttpPost(3),
    Voice(4),
    MobilePush(5),
    Timeline(6),
    Inbox(7),
    Slack(8);

    private Integer value;

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
