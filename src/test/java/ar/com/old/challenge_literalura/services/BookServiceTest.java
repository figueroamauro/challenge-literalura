package ar.com.old.challenge_literalura.services;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceTest {
    @Autowired
    BookRepository repository;
    Book bookSaved;
    Book bookToSave;
    BookService service;

    @BeforeEach
    void init() {
        bookToSave = new Book(null, "test", 100);
        bookSaved = new Book(1L, "test", 100);
        repository = mock(BookRepository.class);
        service = new BookService(repository);
    }

    @Test
    void shouldSaveBook() {
        when(repository.save(bookToSave)).thenReturn(bookSaved);
        Book result = service.saveBook(bookToSave);
        assertEquals(result.getId(), bookSaved.getId());

    }
}
