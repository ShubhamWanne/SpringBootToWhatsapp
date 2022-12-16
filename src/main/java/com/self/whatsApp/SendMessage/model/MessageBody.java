package com.self.whatsApp.SendMessage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageBody {

    @JsonProperty("messaging_product")
    private String messagingProduct;

    @JsonProperty("to")
    private String receiversNumber;

    @JsonProperty("type")
    private String type;

    @JsonProperty("template")
    private MessageTemplate template;

}