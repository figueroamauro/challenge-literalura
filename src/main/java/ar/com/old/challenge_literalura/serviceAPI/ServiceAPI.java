package ar.com.old.challenge_literalura.serviceAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;

public interface ServiceAPI {
    List<JsonElement> getByTitle(String title);
}
