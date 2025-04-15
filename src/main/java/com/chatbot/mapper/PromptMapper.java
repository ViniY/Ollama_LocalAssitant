package com.chatbot.mapper;

import com.chatbot.dto.PromptResponse;
import com.chatbot.model.OllamaChatMsg;

public class PromptMapper {
    public static PromptResponse toResponse(OllamaChatMsg result) {
        return new PromptResponse(result.getResponse());
    }
}
