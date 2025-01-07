package ar.com.old.challenge_literalura.api;

import java.util.List;

public interface ServiceAPI<T> {
    List<T> fetchByTitle(String title);
}
