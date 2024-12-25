package ar.com.old.challenge_literalura.services;

import static org.junit.jupiter.api.Assertions.*;

import ar.com.old.challenge_literalura.models.Author;
import ar.com.old.challenge_literalura.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.mockito.Mockito.*;
import static ar.com.old.challenge_literalura.utils.TestUtils.*;

public class AuthorServiceTest {

    AuthorRepository repository;
    AuthorService service;
    Author authorToSave;
    Author authorSaved;

    @BeforeEach
    void init() {
        repository = mock(AuthorRepository.class);
        service = new AuthorService(repository);
        authorToSave = new Author(null, "test", 1900, 2000);
        authorSaved = new Author(1L, "test", 1900, 2000);
        when(repository.save(authorToSave)).thenReturn(authorSaved);
    }

    @Test
    void shouldSaveAuthor() {
        Author result = service.saveAuthor(authorToSave);
        assertNotNull(result.getId());
        assertEquals(1L, result.getId());
        assertEquals("test", result.getName());
        verify(repository).save(authorToSave);
    }

    @Test
    void shouldThrowException_withNullAuthor() {
        Executable executable = () -> service.saveAuthor(null);
        assertIllegalArgumentException(executable, "El autor no puede ser nulo");
    }


}
