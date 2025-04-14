package service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.PromptRequest;
import dto.PromptResponse;
import lombok.RequiredArgsConstructor;
import model.OllamaChatMsg;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import repository.OllamaChatRepository;


@Service
@RequiredArgsConstructor
public class OllamaService {

    private static final String OLLAMA_URL = "http://localhost:8080/api/generate";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final WebClient.Builder webClientBuilder;
    private final OllamaChatRepository chatRepo;


    public String getResponse(String prompt) {
        PromptRequest request = new PromptRequest("llama3", prompt);
        PromptResponse response = webClientBuilder
                .baseUrl("http://localhost:11434")
                .build()
                .post()
                .uri("/api/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PromptResponse.class)
                .block();
            String reply = response.getResponse().isEmpty()? response.getResponse() : "No response from Ollama.";

        chatRepo.save(OllamaChatMsg.builder()
                .userMsg(prompt)
                .response(reply)
                .timestamp(LocalDateTime.now())
                .build());
                
        return reply;
    }

    public String sendPrompt(String model, String prompt) throws IOException {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("model", model);
        requestJson.addProperty("prompt", prompt);
        requestJson.addProperty("stream", false);

        RequestBody body = RequestBody.create(
            gson.toJson(requestJson), MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(OLLAMA_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected response: " + response);

            JsonObject responseBody = gson.fromJson(response.body().string(), JsonObject.class);
            OllamaChatMsg ollamaChatMsg = OllamaChatMsg.builder().userMsg(prompt).response(responseBody.toString()).timestamp(LocalDateTime.now()).build();
            //TODO SAVE THE OLLAMA CHAT MASSEGe
            return responseBody.toString();
        }
    }
}
