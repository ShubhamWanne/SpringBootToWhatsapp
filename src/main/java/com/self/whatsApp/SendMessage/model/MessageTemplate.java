package com.self.whatsApp.SendMessage.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MessageTemplate {
    private String name;
    private Language language;
}

