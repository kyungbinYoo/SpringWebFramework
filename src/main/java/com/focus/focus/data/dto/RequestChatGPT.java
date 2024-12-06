package com.focus.focus.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestChatGPT {
    private String model;
    private List<Message> messages;
    public RequestChatGPT(String model, String prompt) {
        this.model = model;
        this.messages = List.of(new Message("user", prompt));
    }
}
