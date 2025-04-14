package controller;

import dto.PromptRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.OllamaService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OllamaController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/chat")
    public String chat(@RequestBody PromptRequest request) throws IOException {
        return ollamaService.sendPrompt(request.getModel(), request.getPrompt());
    }
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/")
    public String home() {
        return "Chatbot API is running.";
    }
}
