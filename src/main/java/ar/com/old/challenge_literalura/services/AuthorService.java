package ar.com.old.challenge_literalura.services;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private final AuthorRepository repository;
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author saveAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("El autor no puede ser nulo");
        }
        return repository.save(author);
    }
}
