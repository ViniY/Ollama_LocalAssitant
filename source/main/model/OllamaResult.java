package Ollama_LocalAssitant.model;

public class OllamaResult {
    private String response;

    public OllamaResult() {}
    public OllamaResult(String response) { this.response = response; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
}
