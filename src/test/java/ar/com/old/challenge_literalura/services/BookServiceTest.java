package ar.com.old.challenge_literalura.services;

import static ar.com.old.challenge_literalura.utils.TestUtils.*;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Book;
import ar.com.old.challenge_literalura.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

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
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("test", result.getTitle());
    }

    @Test
    void shouldFailSavingBookAndThrowException_withNullBook(){
        Executable executable = () -> service.saveBook(null);
        assertIllegalArgumentException(executable,"El libro no puede ser nulo");
        verify(repository,never()).save(null);
    }

    @Test
    void shouldFailSavingBookAndThrowException_whenBookAlreadyExists(){
        when(repository.findByTitle("test")).thenReturn(Optional.of(new Book(3L, "test", 200)));
        Executable executable = () -> service.saveBook(bookToSave);
        assertIllegalArgumentException(executable,"El libro ya se encuentra registrado");
        verify(repository,never()).save(bookToSave);
        verify(repository).findByTitle("test");
    }
}
