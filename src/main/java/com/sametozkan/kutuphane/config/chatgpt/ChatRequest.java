package com.sametozkan.kutuphane.config.chatgpt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private double temperature;

    public ChatRequest(String prompt) {
        this.model = "gpt-3.5-turbo";
        this.messages = List.of(new Message("user", prompt));
        this.temperature = 0.0;
    }

}
