package ar.com.old.challenge_literalura.services;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Author> getAllAuthors() {
        Pageable pageable = PageRequest.of(0, 10);
        return repository.findAll(pageable).getContent();
    }

    public Author getAuthorById(Long id) {
        Optional<Author> author = repository.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            throw new IllegalArgumentException("El autor con el id " + id + " no se encuentra registrado");
        }
    }
}
