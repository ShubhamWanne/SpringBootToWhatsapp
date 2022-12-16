package com.self.whatsApp.SendMessage.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.whatsApp.SendMessage.model.Language;
import com.self.whatsApp.SendMessage.model.MessageBody;
import com.self.whatsApp.SendMessage.model.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SendMessageController {

    private static final String URL = "https://graph.facebook.com/v15.0/107179945578557/messages";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(final @Validated @RequestParam("to") String receiversNumber,
                                              final @Validated @RequestParam("template") String templateName,
                                              final @Validated @RequestHeader("Authorization") String authorization) throws JsonProcessingException {
        MessageBody body = MessageBody.builder()
                .messagingProduct("whatsapp")
                .type("template")
                .receiversNumber(receiversNumber)
                .template(
                        MessageTemplate.builder()
                                .name(templateName)
                                .language(
                                        Language.builder()
                                                .code("en_US").build()
                                )
                                .build()
                ).build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", authorization);
        httpHeaders.set("Content-Type", "application/json");

        String request = new ObjectMapper().writeValueAsString(body);

        HttpEntity<String> httpEntity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity(URL, httpEntity, String.class);

        return response;
    }

}
