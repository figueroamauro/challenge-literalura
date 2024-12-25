package ar.com.old.challenge_literalura.services;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    AuthorRepository repository;
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author saveAuthor(Author author) {
        return repository.save(author);
    }
}
