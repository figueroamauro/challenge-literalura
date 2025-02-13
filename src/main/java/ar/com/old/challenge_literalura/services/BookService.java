package ar.com.old.challenge_literalura.services;

import ar.com.old.challenge_literalura.api.GutendexServiceAPI;
import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final GutendexServiceAPI api;

    public BookService(BookRepository repository, GutendexServiceAPI api) {
        this.repository = repository;
        this.api = api;
    }

    public Book saveBook(Book book) {
        validateIsNull(book);
        validateIfExist(book);
        return repository.save(book);
    }

    public Book getBookById(long id) {
        Optional<Book> book = repository.findById(id);
        if (book.isEmpty()) {
            throw new IllegalArgumentException("El libro con id " + id + " no se encuentra registrado");
        }else {
            return book.get();
        }
    }

    public List<Book> getAllBooks() {
        return repository.findAll(Pageable.ofSize(10)).getContent();
    }

    public List<Book>getBookByLanguage(String language) {
        return repository.findAllByLanguages(language);
    }

    public List<Book> fetchBookByTitleInApi(String title) {
        return api.fetchByTitle(title);
    }
    private void validateIfExist(Book book) {
        if (getByTitle(book).isPresent()) {
            throw new IllegalArgumentException("El libro ya se encuentra registrado");
        }
    }

    private void validateIsNull(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("El libro no puede ser nulo");
        }
    }

    private Optional<Book> getByTitle(Book book) {
        return repository.findByTitle(book.getTitle());
    }


    public void deleteAllBooks() {
        repository.deleteAll();
    }
}
