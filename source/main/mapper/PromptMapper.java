import dto.PromptResponse;
import model.OllamaResult;

public class PromptMapper {
    public static PromptResponse toResponse(OllamaResult result) {
        return new PromptResponse(result.getResponse());
    }
}
