package ar.com.old.challenge_literalura.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.models.dto.BookDTO;
import ar.com.old.challenge_literalura.models.mapers.BookMapper;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Service;

import static ar.com.old.challenge_literalura.utils.JsonUtils.*;

@Service
public class GutendexServiceAPI implements ServiceAPI {
    private final HttpClient client;
    private static final String URL = "https://gutendex.com/books/";

    public GutendexServiceAPI() {
        this.client = HttpClient.newHttpClient();

    }

    public List<Book> searchByTitle(String title) {
        HttpRequest request = buildRequest(URL + "?search=" + title.trim().replace(" ", "+"));
        HttpResponse<String> response = sendRequest(request);
        JsonArray array = responseToJsonArray(response, "results");
        return jsonArrayToBookList(array);
    }

    private List<Book> jsonArrayToBookList(JsonArray array) {
        List<BookDTO> bookDtoList = jsonArraytoGenericList(array, BookDTO.class);
        return BookMapper.map(bookDtoList);

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
