package ar.com.old.challenge_literalura.services;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
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
        return repository.findAll(Pageable.ofSize(10)).getContent();
    }

    public List<Author>getAllLivingAuthorsInAGivenYear(int year) {
        return repository.findAuthorsAliveInYear(year);
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
