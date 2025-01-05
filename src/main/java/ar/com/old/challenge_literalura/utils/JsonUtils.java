package ar.com.old.challenge_literalura.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;

public class JsonUtils {

    public static JsonArray responseToJsonArray(HttpResponse<String> response) {
        return JsonParser.parseString(response.body()).getAsJsonObject().getAsJsonArray();
    }

    public static JsonArray responseToJsonArray(HttpResponse<String> response, String elementName) {
        return JsonParser.parseString(response.body()).getAsJsonObject().getAsJsonArray(elementName);
    }
}
