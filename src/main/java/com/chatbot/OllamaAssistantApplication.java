package com.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "Ollama_LocalAssistant")
public class OllamaAssistantApplication {
    public static void main(String[] args) {
        SpringApplication.run(OllamaAssistantApplication.class, args);
    }
}