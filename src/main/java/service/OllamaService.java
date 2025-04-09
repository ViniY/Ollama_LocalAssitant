package service;

import model.OllamaResult;
import com.google.gson.*;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OllamaService {

    private static final String OLLAMA_URL = "http://localhost:8080/api/generate";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

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
            return new OllamaResult(responseBody.get("response").getAsString()).getResponse();
        }
    }
}
