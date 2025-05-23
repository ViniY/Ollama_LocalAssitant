package com.chatbot.dto;

public class PromptRequest {
    private String model;
    private String prompt;


    public PromptRequest(String model, String prompt) {
        this.model = model;
        this.prompt = prompt;
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}
