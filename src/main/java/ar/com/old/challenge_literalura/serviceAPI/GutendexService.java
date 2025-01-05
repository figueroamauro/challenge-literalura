package ar.com.old.challenge_literalura.serviceAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.JsonElement;

import static ar.com.old.challenge_literalura.utils.JsonUtils.responseToJsonArray;

public class GutendexService implements ServiceAPI{
    private final HttpClient client;
    private static final String URL = "https://gutendex.com/books/";

    public GutendexService() {
        this.client = HttpClient.newHttpClient();

    }

    public List<JsonElement> getByTitle(String title) {
        HttpRequest request = buildRequest(URL + "?search=" + title.trim().replace(" ", "+"));
        HttpResponse<String> response = sendRequest(request);
        return  responseToJsonArray(response, "results").asList();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException |
                 InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static HttpRequest buildRequest(String url) {
        return HttpRequest.newBuilder()
                       .uri(URI.create(url))
                       .build();
    }
}
