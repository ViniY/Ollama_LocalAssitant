package mapper;

import dto.PromptResponse;
import model.OllamaChatMsg;

public class PromptMapper {
    public static PromptResponse toResponse(OllamaChatMsg result) {
        return new PromptResponse(result.getResponse());
    }
}
