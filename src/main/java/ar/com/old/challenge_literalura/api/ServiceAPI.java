package ar.com.old.challenge_literalura.api;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ServiceAPI<T> {
    List<T> searchByTitle(String title);
}
