import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/ollama")
public class OllamaController {

    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/chat")
    public String chat(@RequestBody PromptRequest request) throws IOException {
        return ollamaService.prompt(request.getModel(), request.getPrompt());
    }
}
