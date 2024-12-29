package ar.com.old.challenge_literalura.serviceAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexService {
    HttpClient client;
    HttpRequest request;
    public static final String URL = "https://gutendex.com/books/";

    public GutendexService() {
        this.client = HttpClient.newHttpClient();

    }

    public HttpResponse<String> get(String title) {
        this.request = buildRequest("?search=" + title);
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException |
                 InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static HttpRequest buildRequest(String title) {
        return HttpRequest.newBuilder()
                       .uri(URI.create(URL +title))
                       .build();
    }
}
