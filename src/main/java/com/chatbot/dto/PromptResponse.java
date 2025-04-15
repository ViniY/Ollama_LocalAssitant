package com.chatbot.dto;

public class PromptResponse {
    private String response;

    public PromptResponse() {}
    public PromptResponse(String response) { this.response = response; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
}
