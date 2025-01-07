package ar.com.old.challenge_literalura.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;
import java.util.List;

public class JsonUtils<T> {

    public static JsonArray responseToJsonArray(HttpResponse<String> response) {
        return JsonParser.parseString(response.body()).getAsJsonObject().getAsJsonArray();
    }

    public static JsonArray responseToJsonArray(HttpResponse<String> response, String elementName) {
        return JsonParser.parseString(response.body()).getAsJsonObject().getAsJsonArray(elementName);
    }

    public static <T> List<T> jsonArraytoGenericList(JsonArray array, Class<T> generic) {
        Gson gson = new Gson();
        return array.asList().stream().map(element -> gson.fromJson(element, generic)).toList();
    }
}
