package ar.com.old.challenge_literalura.api;

import com.google.gson.JsonElement;

import java.util.List;

public interface ServiceAPI {
    List<JsonElement> getByTitle(String title);
}
